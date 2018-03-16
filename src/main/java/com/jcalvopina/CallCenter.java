package com.jcalvopina;

import com.jcalvopina.domain.Customer;
import com.jcalvopina.service.*;
import com.jcalvopina.utils.DummyData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class.
 *
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public class CallCenter {

    private static final Logger logger = LogManager.getLogger(CallCenter.class);

    public static final int N_DIRECTORES = 1;
    public static final int N_SUPERVISORES = 2;
    public static final int N_OPERADORES = 5;
    public static final int N_CALLS = 10;

    private CustomerService customerService;
    private DispatcherService dispatcherService;

    /**
     * Constructor
     * @param incomingCall receive IncomingCall object
     */
    public CallCenter(IncomingCall incomingCall) {
        EmployeeService employeeService = new EmployeeServiceImpl(incomingCall);
        customerService = new CustomerServiceImpl(incomingCall);
        dispatcherService = new DispatcherServiceImpl(employeeService, customerService);
    }

    /**
     * Main method to run the application
     * @param args does not need parameters
     * @throws InterruptedException Throws an exception
     */
    public static void main(String[] args) throws InterruptedException {
        IncomingCall incomingCall = DummyData.build(N_DIRECTORES, N_SUPERVISORES, N_OPERADORES, N_CALLS);
        CallCenter callCenter = new CallCenter(incomingCall);
        callCenter.init();
    }

    /**
     * Start the calls
     *
     * @throws InterruptedException Throws the exception for finishDispatch
     */
    public void init() throws InterruptedException {
        Long callId = 0L;
        logger.info("{CallId: " + callId);
        Customer currentCustomer = customerService.getCustomer();
        logger.info(currentCustomer);

        while (currentCustomer != null) {
            getDispatcherService().dispatchCall(callId, currentCustomer);
            callId++;
            currentCustomer = customerService.getCustomer();
        }
        this.getDispatcherService().finishDispatch();
    }

    /**
     * Getter for DispatcherService
     *
     * @return DispatcherService
     */
    public DispatcherService getDispatcherService() {
        return dispatcherService;
    }

}
