package com.jcalvopina.service;

import com.jcalvopina.domain.Employee;
import com.jcalvopina.domain.EmployeeStatus;
import com.jcalvopina.domain.EmployeeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

import static com.jcalvopina.domain.EmployeeStatus.AVAILABLE;
import static com.jcalvopina.domain.EmployeeStatus.BUSY;

/**
 * This class allows manipulate the employees
 *
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    private Map<String, Deque<Employee>> shiftEmployees;

    public EmployeeServiceImpl(IncomingCall incomingCall) {
        this.shiftEmployees = incomingCall.findAllEmployeesInShift();
    }

    /**
     * @return An available employee
     */
    @Override
    public Employee findAvailableEmployee() {

        Employee currentEmployee = null;
        Boolean isAvailable = false;
        int typeOfEmployeeIndex = 0;

        while (typeOfEmployeeIndex < shiftEmployees.size() && !isAvailable) {
            byte employeeIndex = 0;

            List<EmployeeType> employeeTypeList = Arrays.asList(EmployeeType.values());
            String currentEmployeeType = employeeTypeList.get(typeOfEmployeeIndex).name();

            Deque<Employee> employeesByType = shiftEmployees.get(currentEmployeeType);

            logger.debug("employeesByType: ");
            employeesByType.forEach(logger::info);

            while (employeeIndex < employeesByType.size()) {
                currentEmployee = employeesByType.poll();

                logger.debug("currentEmployee: " + currentEmployee);

                employeesByType.offer(currentEmployee);

                if (currentEmployee.getEmployeeStatus().equals(AVAILABLE)) {
                    logger.info("currentEmployee: " + currentEmployee);
                    isAvailable = true;
                    currentEmployee.setEmployeeStatus(BUSY);
                    break;
                } else {
                    currentEmployee = null;
                    employeeIndex++;
                }

            }
            typeOfEmployeeIndex++;
        }
        return currentEmployee;
    }

    /**
     * @param status receive a employee status
     * @return All employees that matching with the status
     */
    @Override
    public List<Employee> findEmployeesByStatus(EmployeeStatus status) {
        List<Employee> filteredEmployees = shiftEmployees
                .values()
                .stream()
                .flatMap(Collection::stream)
                .filter(e -> status.equals(e.getEmployeeStatus()))
                .collect(Collectors.toList());

        return Collections.unmodifiableList(filteredEmployees);
    }

}
