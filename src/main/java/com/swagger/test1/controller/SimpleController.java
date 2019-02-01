package com.swagger.test1.controller;

import com.swagger.test1.model.User;
import com.swagger.test1.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/home")
@Api(value = "CRUD over users of DB", tags = {"user_operations"})
public class SimpleController
{

    private static final String HOME_VIEW = "home";

    @Autowired
    @Qualifier("userService")
    UserService userService;


    @GetMapping(value = "", produces = "text/html")
    @ApiOperation(value = "Get home view", notes = "Gets the home view that contains CRUD operations for users of DB", authorizations = {@Authorization("test authorization")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Home view accessed", examples = @Example(value  = @ExampleProperty(value = HOME_VIEW, mediaType = "text/html"))),
            @ApiResponse(code = 404, message = "Just use path /home")
    })
    public String getHomeView(Model model) {
        model.addAttribute("user", new User());
        return HOME_VIEW;
    }

    @GetMapping(value = "/userList", produces = "application/json")
    @ApiOperation(value = "Fetches all users", notes = "Returns iterable with all users in DB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched users", responseContainer = "List"),
            @ApiResponse(code = 404, message = "Just use path /userList")
    })
    public User[] listUsers() {
        Iterable<User> userIterable = userService.findAll();
        ArrayList<User> users = new ArrayList<>();
        for (User user : userIterable) {
            users.add(user);
        }
        return users.toArray(new User[users.size()]);

    }


    @GetMapping(value = "/find/{id}", produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "Fetches specific user", notes = "Returns User with specified id", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched user"),
            @ApiResponse(code = 404, message = "Just use path /find/{id}")
    })
    public User findUser(@ApiParam(value = "ID that identifies user to fetch", required = true) @PathVariable("id") Long id) {
        return userService.find(id);
    }


    @PutMapping(value = "/update", consumes = "application/json")
    @ApiOperation(value = "Update user data", notes = "Receives an user and updates its value in database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated user"),
            @ApiResponse(code = 404, message = "Just use path /update")
    })
    public void updateUser(@ApiParam(value = "User object used to update an user", required = true) @RequestBody User user) {
        userService.save(user);
        /*new ResponseEntity("User data updated", HttpStatus.OK);*/
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Deletes specified user", notes = "Receives user id and deletes user from DB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted user"),
            @ApiResponse(code = 404, message = "Just use path /delete/{id}")
    })
    public void deleteUser(@ApiParam(value = "ID that identifies user to delete", required = true) @PathVariable("id") Long id) {
        userService.delete(id);
        /*return new ResponseEntity("User deleted", HttpStatus.OK);*/

    }

}
