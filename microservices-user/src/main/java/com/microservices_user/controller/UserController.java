package com.microservices_user.controller;

import com.microservices_user.dto.UserDTO;
import com.microservices_user.dto.UserLoginDTO;
import com.microservices_user.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        return ResponseEntity.ok(userService.findByName(name));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<?> findByRole(@PathVariable String role){
        return ResponseEntity.ok(userService.findByRole(role));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> findByStatus(@PathVariable String  status){
        return ResponseEntity.ok(userService.findByStatus(status));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UserDTO userDTO){
        userService.save(userDTO);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id,  @RequestBody UserDTO userDTO){
        userService.update(id,userDTO);
    }

    @PutMapping("/updateStatus/{id}/{status}")
    public void updateStatus(@PathVariable Long id,@PathVariable String status){
        userService.updateStatus(id, status);        ;
    }

    @PutMapping("/updateRole/{id}/{role}")
    public void updateRole(@PathVariable Long id,@PathVariable String role){
        userService.updateRole(id, role);
    }

    @PutMapping("/saveCredentials/{id}")
    public void saveCredentials(@PathVariable Long id, @RequestBody UserLoginDTO userDTO){
        userService.saveCredentials(id, userDTO);
    }

    @PutMapping("/updateCredentials/{id}")
    public void updateCredentials(@PathVariable Long id, @RequestBody UserLoginDTO userDTO){
        userService.updateCredentials(id, userDTO);
    }

/*    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO){
        return ResponseEntity.ok(userService.login(userLoginDTO));
    }*/

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

}
