package org.ttn.sprintrest2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;
import org.ttn.sprintrest2.entity.User;
import org.ttn.sprintrest2.service.UserService;

import java.util.List;
import java.util.Locale;

@RestController
public class Controller {

    @Autowired
    MessageSource messageSource;

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET,path = "/welcome/{username}")
    public String welcome(@PathVariable String username){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("welcome.message",null,"Default message",locale) + username;
    }

    @RequestMapping(method = RequestMethod.GET,path = "/get",produces = "application/xml")
    public List<User> getUser(){
        return userService.getUsers();
    }


    @RequestMapping(method = RequestMethod.POST,path = "/add",consumes = "application/xml",produces = {"application/json"})
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }


    @RequestMapping(method = RequestMethod.POST,path = "/add",consumes = "application/json",produces = "application/json")
    public User addUserJson(@RequestBody User user){
        return userService.addUser(user);
    }
    @RequestMapping(method = RequestMethod.DELETE,path = "/delete/{id}",produces = "application/xml")
    public User deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }


}
