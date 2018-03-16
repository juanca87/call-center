package com.jcalvopina.service;

import com.jcalvopina.domain.Customer;

/**
 * Interface for CustomerServiceImpl
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public interface CustomerService {

    Customer getCustomer();

    void addCustomer(Customer customer);

}
