package ru.gothmog.one_to_one.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gothmog.one_to_one.domain.Employee;
import ru.gothmog.one_to_one.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Object list() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        employee.getAddress().setEmployee(employee);
        return employeeRepository.save(employee);
    }

    public Employee get(long id) {
        return employeeRepository.getOne(id);
    }

    public Employee update(Employee employee) {
        Employee old = employeeRepository.getOne(employee.getId());
        old.setName(employee.getName());
        old.setEmail(employee.getEmail());
        old.setPassword(employee.getPassword());
        old.getAddress().setAddress(employee.getAddress().getAddress());
        employee = employeeRepository.save(old);
        return employee;
    }

    public void delete(long id) {
        employeeRepository.deleteById(id);
    }
}
