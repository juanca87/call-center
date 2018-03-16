package com.jcalvopina.service;

import com.jcalvopina.domain.Customer;

import java.util.Deque;

/**
 * This class allows manipulate the customers (add or remove)
 *
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public class CustomerServiceImpl implements CustomerService {

    private Deque<Customer> customers;

    public CustomerServiceImpl(IncomingCall incomingCall) {
        this.customers = incomingCall.findAllCustomersQueued();
    }

    /**
     * Get and Remove the customer from the beginning of the collection
     *
     * @return Customer
     */
    @Override
    public Customer getCustomer() {
        return customers.poll();
    }

    /**
     * Add a customer at the beginning of the collection
     *
     * @param customer receive a Customer object
     */
    @Override
    public void addCustomer(Customer customer) {
        customers.offerFirst(customer);
    }

}
