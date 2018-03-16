package com.jcalvopina.service;

import com.jcalvopina.domain.Customer;
import com.jcalvopina.domain.Employee;

import java.util.Deque;
import java.util.Map;

/**
 * Interface for IncomingCallImpl
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public interface IncomingCall {

    void addEmployeeToShift(Employee employee);

    Map<String, Deque<Employee>> findAllEmployeesInShift();

    Deque<Customer> findAllCustomersQueued();

    void addCustomer(Customer customer);

}
