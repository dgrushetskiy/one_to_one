package ru.gothmog.one_to_one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gothmog.one_to_one.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    List<Employee> findAll();
//

}
