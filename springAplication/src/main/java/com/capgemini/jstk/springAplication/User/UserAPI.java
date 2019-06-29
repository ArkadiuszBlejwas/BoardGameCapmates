package com.capgemini.jstk.springAplication.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAPI {

    private UserService userService;

    @Autowired
    public UserAPI(UserService userService){
        this.userService = userService;
    }

    /**
     * This method finds users with specified parameters
     * @param userDTO object which has specified parameters
     * @return ResponseEntity with http status 200 and users with specified parameters
     */
    @PostMapping(value = "/filterUser")
    public ResponseEntity<List<UserDTO>> filterUser(@RequestBody UserDTO userDTO){
        List<UserDTO> filterList = userService.findUserByParameters(userDTO);
        return ResponseEntity.ok().body(filterList);
    }

    /**
     * This method get all users from database
     * @return ResponseEntity with http status 200 and all users from database
     */
    @GetMapping(value = "/getUsers")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> list = userService.getAllUsers();
        return ResponseEntity.ok().body(list);
    }

    /**
     * This method get user with specified id
     * @param id identity number of user
     * @return ResponseEntity with http status 200 and user with specified id
     */
    @GetMapping(value = "/getUser/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    /**
     * This method adds new user to database
     * @param userDTO new user
     * @return ResponseEntity with http status 200 and new user
     */
    @PostMapping(value = "/addUserProfile")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
        userService.addUserProfile(userDTO);
        return ResponseEntity.ok().body(userDTO);
    }
}
