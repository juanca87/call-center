package com.jcalvopina.service;

import com.jcalvopina.domain.Customer;
import com.jcalvopina.domain.Employee;
import org.junit.Before;
import org.junit.Test;

import static com.jcalvopina.domain.EmployeeType.OPERADOR;

/**
 * Test case for dispatch the calls.
 *
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public class DispatcherServiceImplTest {

    private static final int NUMBER_OF_CALLS = 100;

    private DispatcherService dispatcherService;
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        Employee employee = new Employee("OPERADOR 1", OPERADOR);
        IncomingCall incomingCall = new IncomingCallImpl();
        incomingCall.addEmployeeToShift(employee);
        customer = new Customer("CUSTOMER 1");
        dispatcherService = new DispatcherServiceImpl(new EmployeeServiceImpl(incomingCall), new CustomerServiceImpl(incomingCall));
    }

    /**
     * This test runs all calls by the same customer and are assigned to the same employee.
     *
     * @throws InterruptedException Throws the exception for finishDispatch
     */
    @Test
    public void runCallTest() throws InterruptedException {
        for (int customerCallId = 1; customerCallId <= NUMBER_OF_CALLS; customerCallId++) {
            dispatcherService.dispatchCall((long) customerCallId, customer);
        }
        dispatcherService.finishDispatch();
    }

    /**
     * only one employee available to answer the calls
     *
     * @throws InterruptedException Throws the exception for finishDispatch
     */
    @Test
    public void runOneCallTest() throws InterruptedException {
        dispatcherService.dispatchCall(1L, customer);
        dispatcherService.finishDispatch();
    }

}
