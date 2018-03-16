package com.jcalvopina.service;

import com.jcalvopina.domain.Customer;

/**
 * Interface for DispatcherServiceImpl
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public interface DispatcherService {

    void dispatchCall(Long customerCallId, Customer customer);

    void finishDispatch() throws InterruptedException;

}
