package com.jcalvopina.service;

import com.jcalvopina.domain.Customer;
import com.jcalvopina.domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.jcalvopina.domain.EmployeeStatus.AVAILABLE;

/**
 * Dispatch the incoming calls. This creates a pool of 10 threads is limited in MAX_NUMBER_SIM_CALLS
 *
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public class DispatcherServiceImpl implements DispatcherService {

    private static final Logger logger = LogManager.getLogger(DispatcherServiceImpl.class);
    private static final int MAX_NUMBER_SIM_CALLS = 10;

    private ExecutorService executor;
    private EmployeeService employeeService;
    private CustomerService customerService;

    public DispatcherServiceImpl(EmployeeService employeeService, CustomerService customerService) {
        this.executor = Executors.newFixedThreadPool(MAX_NUMBER_SIM_CALLS);
        this.employeeService = employeeService;
        this.customerService = customerService;
    }

    /**
     * If there is an employee available then starts a call in a new thread, otherwise,
     * wait for an employee available who can attend the call.
     *
     * @param customerCallId callId
     * @param customer retrieves a Customer object
     */
    @Override
    public void dispatchCall(Long customerCallId, Customer customer) {

        Employee nextAvailableEmployee = employeeService.findAvailableEmployee();

        if (nextAvailableEmployee != null) {

            logger.info("nextAvailableEmployee :" + nextAvailableEmployee);

            Runnable call = new CallServiceImpl(customerCallId, customer, nextAvailableEmployee);
            executor.execute(call);
        } else {
            logger.info("There are no employees available to answer the call. {attempt=" + customerCallId
                        + ", customer=" + customer.getName()
                        + ", employeesAvailable=" + employeeService.findEmployeesByStatus(AVAILABLE).size()
                        + "}");

            customerService.addCustomer(customer);
        }
    }

    /**
     * Finishes the treads
     *
     * @throws InterruptedException Throws the exception for awaitTermination invocation
     */
    @Override
    public void finishDispatch() throws InterruptedException {
        executor.shutdown();
        executor.awaitTermination(11, TimeUnit.SECONDS);
        logger.info("All threads finished.");
    }

}
