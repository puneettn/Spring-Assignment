package org.ttn.springrest.service;

import org.springframework.stereotype.Component;
import org.ttn.springrest.entity.Employee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeService {
  //  public static List<Employee> list = new ArrayList<>();
    public static HashSet<Employee> list =  new HashSet<>();
    static{
        list.add(new Employee(1,"Puneet",24));
    }
    public Employee addEmployee(Employee emp){
        list.add(emp);
        return emp;
    }

    public HashSet<Employee> getEmployee(){
        return list;
    }

    public Employee  getParticularEmployee(int id){
     return list.stream().filter(e->e.getId() == id).findFirst().orElse(null);

    }


    public HashSet<Employee> deleteEmployee(int id){
    HashSet<Employee> rem = new HashSet<>();
      Optional<Employee> emp =   list.stream().filter(e->e.getId() == id).findFirst();
      emp.get().toString();
      if(emp.isPresent()){

          rem.add(emp.get());
          list.removeAll(rem);
          return  list;
      }else{
          return null;
      }


    }

    public Employee updateEmployee(Employee emp){

        Optional<Employee> employee =   list.stream().filter(e->e.getId() ==emp.getId() ).findFirst();
        if(employee.isPresent()){
            employee.get().setName(emp.getName());
            employee.get().setAge(emp.getAge());
             return employee.get();
        }else{
            return null;
        }
    }
}



