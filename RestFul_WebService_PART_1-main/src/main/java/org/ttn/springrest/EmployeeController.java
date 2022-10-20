package org.ttn.springrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.ttn.springrest.entity.Employee;
import org.ttn.springrest.exception.EmployeeNotfoundException;
import org.ttn.springrest.exception.UserNotFoundException;
import org.ttn.springrest.service.EmployeeService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@Component
@RestController
public class EmployeeController {


    @Autowired
    EmployeeService eService;

    @RequestMapping(path = "/")
    public String welcome(){
        return "Welcome to spring boot";
    }

    @RequestMapping(method = RequestMethod.POST,path = "/add")
    public Employee addEmp(@Valid @RequestBody Employee emp){

     try{
        eService.addEmployee(emp);
    }catch (Exception e){
        e.printStackTrace();
    }
    return emp;

    }


    @RequestMapping(method = RequestMethod.GET,path="/get")
    public HashSet<Employee> getEmp(){
        return eService.getEmployee();
    }


    @RequestMapping(method = RequestMethod.GET,path = "/get/{id}")
    public ResponseEntity<Object> getParticularEmployee(@PathVariable int id){
      Employee emp =  eService.getParticularEmployee(id);
        if(emp == null){
            throw new EmployeeNotfoundException();
        }
        return new ResponseEntity<>(emp, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.DELETE,path = "/delete/{id}")
    public ResponseEntity<Object> delEmp(@PathVariable int id){
    HashSet<Employee> list = eService.deleteEmployee(id);
      if( list==null){
          throw new EmployeeNotfoundException();

      }
        return new ResponseEntity<>(list, HttpStatus.OK);

    }



    @RequestMapping(method = RequestMethod.PUT,path = "/update")
    public ResponseEntity<Object> updateEmp(@RequestBody Employee emp){
        Employee obj = eService.updateEmployee(emp);
        if(obj== null){
            throw new EmployeeNotfoundException();
       }else{
            return new ResponseEntity<>(obj, HttpStatus.OK);
       }

    }
}
