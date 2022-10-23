package org.ttn.sprintrest2.service;

import org.springframework.stereotype.Component;
import org.ttn.sprintrest2.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

   public static  List<User>  list = new ArrayList<>();
   static {
       list.add(new User(1,"Puneet","1256"));
       list.add(new User(2,"Lalit","4656"));

   }

   public List<User> getUsers(){
       return list;
   }

   public User addUser(User user){
       list.add(user);
       return user;
   }

   public User deleteUser(int id){
       Optional<User> emp = list.stream().filter(e->e.getId() == id).findFirst();
       List<User> li = new ArrayList<>();

       if(emp.isPresent()){
           li.add(emp.get());
           list.removeAll(li);
       }
       return li.get(0);

   }
}
