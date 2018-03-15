package com.jcalvopina.domain;

/**
 * The Customer class represents the person who will call to the call center.
 *
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public class Customer extends Person {

    public Customer(String customerName) {
        name = customerName;
    }

    @Override
    public String toString() {
        return "Customer: {" +
                "name='" + name + '\'' +
                '}';
    }

}
