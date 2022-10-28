package com.SpringDataJpa.JpaPart_1;

import com.SpringDataJpa.JpaPart_1.entity.Employee;
import com.SpringDataJpa.JpaPart_1.repos.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JpaPart1ApplicationTests {
	@Autowired
	EmployeeRepository employeeRepository ;

	@Test
	void contextLoads() {
	}
	@Test
      public void createTest() {
		Employee employee = new Employee();
		employee.setId(3);
		employee.setAge(23);
		employee.setName("Sumeet");
		employee.setLocation("Uri");
		employeeRepository.save(employee) ;

	}



	@Test
	public  void UpdateTest(){
		Employee employee = employeeRepository.findById(1).get();
		employee.setLocation("Gurugram");
		employeeRepository.save(employee);
	}

	@Test
	public  void deleteTest(){
		employeeRepository.deleteById(1);
	}

	@Test
	public  void readTest(){
		Employee employee = employeeRepository.findById(1).get();
		assertNotNull(employee);
		assertEquals("Noida",employee.getLocation());
	}

	@Test
	public void countTest(){
		System.out.println(">>>>>total rows: " + employeeRepository.count() + "<<<<<");
	}

	@Test
	public void pagableTest(){
		Pageable paging = PageRequest.of(0, 2, Sort.by(Sort.Order.asc("name")));
		Page<Employee> pagedResult = employeeRepository.findAllByAge(22,paging);
		pagedResult.forEach(e-> System.out.println(e.getName()));
	}

	@Test
	public void findByNameTest(){
		List<Employee> employees = employeeRepository.findByName("Puneet");
		employees.forEach(e-> System.out.println(e.getLocation()));
	}

	@Test
	public void findByDescLikeTest(){
		List<Employee> employees = employeeRepository.findByNameLike("A%");
		employees.forEach(e-> System.out.println(e.getLocation()));
	}

	@Test
	public void findByAgeBetween(){
		List<Employee> employees = employeeRepository.findByAgeBetween(20,28);
		employees.forEach(e-> System.out.println(e.getName()));
	}
}
