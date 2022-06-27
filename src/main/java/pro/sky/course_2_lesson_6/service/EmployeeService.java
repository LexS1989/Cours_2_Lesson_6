package pro.sky.course_2_lesson_6.service;

import pro.sky.course_2_lesson_6.exception.EmployeeAlreadyAddedException;
import pro.sky.course_2_lesson_6.exception.EmployeeNotFoundException;
import pro.sky.course_2_lesson_6.exception.EmployeeStorageIsFullException;
import pro.sky.course_2_lesson_6.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private static final int LIMIT = 5;

    private final List<Employee> employees = new ArrayList<>(10);

    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }
}
