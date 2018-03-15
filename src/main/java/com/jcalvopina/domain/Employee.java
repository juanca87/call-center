package com.jcalvopina.domain;

import static com.jcalvopina.domain.EmployeeStatus.AVAILABLE;

/**
 * The Employee class represent the person who will answer the incoming call,
 * this has a status (AVAILABLE or BUSY) and a type (OPERATOR, SUPERVISOR or DIRECTOR)
 *
 * @author Juan Calvopina M. <juan.calvopina@gmail.com>
 */
public class Employee extends Person {

    private EmployeeStatus employeeStatus;
    private EmployeeType employeeType;

    public Employee(String employeeName, EmployeeType employeeType) {
        name = employeeName;
        this.employeeType = employeeType;
        this.employeeStatus = AVAILABLE;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    @Override
    public String toString() {
        return "Employee: {" +
                "employeeStatus=" + employeeStatus +
                ", employeeType=" + employeeType +
                ", name='" + name + '\'' +
                '}';
    }

}
