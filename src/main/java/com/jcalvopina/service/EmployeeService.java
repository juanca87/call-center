package com.jcalvopina.service;

import com.jcalvopina.domain.Employee;
import com.jcalvopina.domain.EmployeeStatus;

import java.util.List;

/**
 * Interface for EmployeeServiceImpl
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public interface EmployeeService {

    Employee findAvailableEmployee();

    List<Employee> findEmployeesByStatus(EmployeeStatus status);

}
