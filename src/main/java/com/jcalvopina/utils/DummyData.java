package com.jcalvopina.utils;

import com.jcalvopina.domain.Customer;
import com.jcalvopina.domain.Employee;
import com.jcalvopina.domain.EmployeeType;
import com.jcalvopina.service.IncomingCall;
import com.jcalvopina.service.IncomingCallImpl;

import static com.jcalvopina.domain.EmployeeType.*;

/**
 * This is a class that create a dummy data
 *
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public class DummyData {

    /**
     * Create n employees and n customers
     *
     * @param nDirector   number of DIRECTORES will that be created
     * @param nSupervisor number of SUPERVISORES will that be created
     * @param nOperador   number of OPERADORES will that be created
     * @param nCall       number of CALLS that CUSTOMER make
     * @return IncomingCallImpl object
     */
    public static IncomingCall build(int nDirector, int nSupervisor, int nOperador, int nCall) {
        IncomingCall incomingCall = new IncomingCallImpl();

        // Creates Director Employees
        createEmployee(incomingCall, nDirector, DIRECTOR);

        // Creates Supervisor Employees
        createEmployee(incomingCall, nSupervisor, SUPERVISOR);

        // Creates Supervisor Employees
        createEmployee(incomingCall, nOperador, OPERADOR);

        // Creates Customers Calls
        createCustomerCall(incomingCall, nCall);

        return incomingCall;
    }

    /**
     * Create employees by EmployeeType
     *
     * @param incomingCall      receive a IncomingCallImpl object
     * @param numberOfEmployees receives the number of employees that will be created
     * @param employeeType      receive the EmployeeType will be created
     */
    private static void createEmployee(IncomingCall incomingCall, int numberOfEmployees, EmployeeType employeeType) {
        for (int i = 1; i <= numberOfEmployees; i++) {
            Employee director = new Employee(employeeType.name() + " " + i, employeeType);
            incomingCall.addEmployeeToShift(director);
        }
    }

    /**
     * Create customers
     *
     * @param incomingCall receives a IncomingCallImpl object
     * @param nCall        number of CALLS that CUSTOMER make
     */
    private static void createCustomerCall(IncomingCall incomingCall, int nCall) {
        for (int i = 1; i <= nCall; i++) {
            Customer customer = new Customer("Customer " + i);
            incomingCall.addCustomer(customer);
        }
    }

}
