package com.jcalvopina.service;

import com.jcalvopina.domain.Customer;
import com.jcalvopina.domain.Employee;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static com.jcalvopina.domain.EmployeeType.*;

/**
 * This class allows manipulate the incoming calls
 *
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public class IncomingCallImpl implements IncomingCall {

    private Map<String, Deque<Employee>> employeesInShift;
    private Deque<Customer> queuedCustomers;

    public IncomingCallImpl() {
        employeesInShift = new HashMap<>();
        queuedCustomers = new LinkedList<>();

        employeesInShift.put(OPERADOR.name(), new LinkedList<>());
        employeesInShift.put(SUPERVISOR.name(), new LinkedList<>());
        employeesInShift.put(DIRECTOR.name(), new LinkedList<>());
    }

    /**
     * Adds employee to shift
     *
     * @param employee receive an Employee object
     */
    @Override
    public void addEmployeeToShift(Employee employee) {
        employeesInShift.get(employee.getEmployeeType().name()).push(employee);
    }

    /**
     * @return All employees in shift
     */
    @Override
    public Map<String, Deque<Employee>> findAllEmployeesInShift() {
        return employeesInShift;
    }

    /**
     * @return All customers queued
     */
    @Override
    public Deque<Customer> findAllCustomersQueued() {
        return queuedCustomers;
    }

    /**
     * Adds a new customer to the queue
     *
     * @param customer receive a Customer object
     */
    @Override
    public void addCustomer(Customer customer) {
        queuedCustomers.add(customer);
    }

}
