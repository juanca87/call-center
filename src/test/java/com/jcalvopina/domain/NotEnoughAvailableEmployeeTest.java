package com.jcalvopina.domain;

import com.jcalvopina.CallCenter;
import com.jcalvopina.service.IncomingCall;
import com.jcalvopina.utils.DummyData;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs 5 calls to the CallCenter and there are only two available employees to take calls.
 *
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public class NotEnoughAvailableEmployeeTest {

    private CallCenter callCenter;

    @Before
    public void setUp() throws Exception {
        IncomingCall incomingCall = DummyData.build(0, 1, 1, 5);
        callCenter = new CallCenter(incomingCall);
    }

    /**
     * Runs the calls
     *
     * @throws InterruptedException Throws the exception for finishDispatch
     */
    @Test
    public void runCallTest() throws InterruptedException {
        callCenter.init();
        callCenter.getDispatcherService().finishDispatch();
    }

}
