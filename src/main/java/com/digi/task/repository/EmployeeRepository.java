package com.digi.task.repository;

import com.digi.task.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

}
