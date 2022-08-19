package sit.int221.oasipserver.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import sit.int221.oasipserver.dtos.CreateUserDto;
import sit.int221.oasipserver.dtos.UserDetailDto;
import sit.int221.oasipserver.dtos.UserDto;
import sit.int221.oasipserver.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired UserService userService;
    @Autowired private ModelMapper modelMapper;

    @GetMapping("")
    public List<UserDto> getAllUser() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDetailDto getUserById(@PathVariable Integer id) {
        return modelMapper.map(userService.getById(id), UserDetailDto.class);
    }

    @PostMapping("")
    public UserDto createUser(@Valid @RequestBody CreateUserDto newUser, BindingResult result) throws MethodArgumentNotValidException {
        return userService.create(newUser, result);
    }
}
