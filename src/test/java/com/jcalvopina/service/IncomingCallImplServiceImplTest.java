package com.jcalvopina.service;

import com.jcalvopina.CallCenter;
import com.jcalvopina.utils.DummyData;
import org.junit.Before;
import org.junit.Test;

import static com.jcalvopina.CallCenter.*;

/**
 * Test case for runs 10 calls.
 *
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public class IncomingCallImplServiceImplTest {

    private CallCenter callCenter;

    @Before
    public void setUp() throws Exception {
        IncomingCall incomingCall = DummyData.build(N_DIRECTORES, N_SUPERVISORES, N_OPERADORES, N_CALLS);

        callCenter = new CallCenter(incomingCall);
    }

    @Test
    public void runTenCallsTest() throws InterruptedException {
        callCenter.init();
    }

}
