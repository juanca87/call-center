package com.jcalvopina.domain;

import com.jcalvopina.service.EmployeeService;
import com.jcalvopina.service.EmployeeServiceImpl;
import com.jcalvopina.service.IncomingCall;
import com.jcalvopina.service.IncomingCallImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.jcalvopina.domain.EmployeeStatus.AVAILABLE;
import static com.jcalvopina.domain.EmployeeStatus.BUSY;
import static com.jcalvopina.domain.EmployeeType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EmployeeTest {

    private Employee operador1, operador2, operador3, supervisor1, supervisor2, director;
    private IncomingCall incomingCall;
    private EmployeeService employeeService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        operador1 = new Employee("OPERADOR 1", OPERADOR);

        operador2 = new Employee("OPERADOR 2", OPERADOR);
        operador2.setEmployeeStatus(BUSY);

        operador3 = new Employee("OPERADOR 3", OPERADOR);
        operador3.setEmployeeStatus(BUSY);

        supervisor1 = new Employee("SUPERVISOR 1", SUPERVISOR);
        supervisor2 = new Employee("SUPERVISOR 2", SUPERVISOR);
        supervisor2.setEmployeeStatus(BUSY);

        director = new Employee("DIRECTOR 1", DIRECTOR);
        director.setEmployeeStatus(BUSY);

        incomingCall = new IncomingCallImpl();
        incomingCall.addEmployeeToShift(operador1);
        incomingCall.addEmployeeToShift(operador2);
        incomingCall.addEmployeeToShift(operador3);
        incomingCall.addEmployeeToShift(supervisor1);
        incomingCall.addEmployeeToShift(supervisor2);
        incomingCall.addEmployeeToShift(director);

        employeeService = new EmployeeServiceImpl(incomingCall);
    }

    @Test
    public void shouldBeSameEmployee() {
        assertEquals("Different employees", operador1, operador1);
    }

    @Test
    public void shouldNotBeSameEmployee() {
        assertNotEquals("Same employees", operador1, operador2);
    }

    @Test
    public void shouldBeSameOperatorsNumber() {
        assertEquals("Not the right number of operators",
                     incomingCall.findAllEmployeesInShift().get(OPERADOR.name()).size(), 3);
    }

    @Test
    public void shouldBeSameSupervisorsNumber() {
        assertEquals("Not the right number of supervisors",
                     incomingCall.findAllEmployeesInShift().get("SUPERVISOR").size(), 2);
    }

    @Test
    public void shouldBeSameDirectorsNumber() {
        assertEquals("Not the right number of directors",
                     incomingCall.findAllEmployeesInShift().get("DIRECTOR").size(), 1);
    }

    @Test
    public void shouldGetAvailableEmployee() {
        assertEquals(employeeService.findAvailableEmployee().getName(), "OPERADOR 1");
        assertEquals(employeeService.findAvailableEmployee().getName(), "SUPERVISOR 1");
    }

    @Test
    public void shouldGetEmployeesBusy() {
        assertEquals(employeeService.findEmployeesByStatus(BUSY).size(), 4);
    }

    @Test
    public void shouldGetEmployeesAvailable() {
        assertEquals(employeeService.findEmployeesByStatus(AVAILABLE).size(), 2);
    }

}
