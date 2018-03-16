package com.jcalvopina.service;

import com.jcalvopina.domain.Customer;
import com.jcalvopina.domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.jcalvopina.domain.EmployeeStatus.AVAILABLE;

/**
 * This is a simulation of incoming call, this has a random duration between 5 and 10 seconds. A call starts
 * with run() method from Runnable interface, when the call finishes, the employee status is set back to "AVAILABLE".
 *
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public class CallServiceImpl implements CallService {

    private static final Logger logger = LogManager.getLogger(CallServiceImpl.class);
    private static final Integer MIN_CALL_TIME = 5000;
    private static final Integer MAX_CALL_TIME = 10000;

    private Long id;
    private Employee employee;
    private Customer customer;

    /**
     * Constructor
     *
     * @param id       idCall
     * @param customer receive a Customer object
     * @param employee receive a Employee object
     */
    CallServiceImpl(Long id, Customer customer, Employee employee) {
        this.id = id;
        this.customer = customer;
        this.employee = employee;
    }

    /**
     * Retrieves random integers within specified min and max values
     *
     * @param min Minimum random number
     * @param max Maximum random number
     * @return A random Integer between min and max
     */
    private Long getRandomTime(Integer min, Integer max) {
        Integer number;
        if (min == null || max == null) {
            throw new IllegalArgumentException("min and max values must be different of null");
        } else if (min == 0 && max == 0) {
            throw new IllegalArgumentException("min and max values must be greater than zero");
        } else if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        } else if (min.equals(max)) number = min;
        else {
            Random r = new Random();
            number = r.nextInt((max - min) + 1) + min;
        }
        return number.longValue();
    }

    @Override
    public void run() {
        Long threadDuration = this.getRandomTime(MIN_CALL_TIME, MAX_CALL_TIME);

        logger.info("Start: { Id=" + id
                    + ", Duration=" + TimeUnit.MILLISECONDS.toSeconds(threadDuration)
                    + " seg, " + customer
                    + ", " + employee
                    + "}");

        try {
            Thread.sleep(threadDuration);

            logger.info("Ended: { Id=" + id
                        + ", " + customer
                        + ", " + employee
                        + "}");

            employee.setEmployeeStatus(AVAILABLE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
