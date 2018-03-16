package com.jcalvopina;

import com.jcalvopina.domain.EmployeeTest;
import com.jcalvopina.domain.NotEnoughAvailableEmployeeTest;
import com.jcalvopina.service.DispatcherServiceImplTest;
import com.jcalvopina.service.IncomingCallImplServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({DispatcherServiceImplTest.class, EmployeeTest.class, IncomingCallImplServiceImplTest.class, NotEnoughAvailableEmployeeTest.class})
public class AllTests {
}
