package com.SpringDataJpa.JpaPart_1.repos;

import com.SpringDataJpa.JpaPart_1.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByName(String name);
    List<Employee> findByNameLike(String desc);
    List<Employee> findByAgeBetween(int age1, int age2);
    Page<Employee> findAllByAge(int age, Pageable pageable) ;
}
