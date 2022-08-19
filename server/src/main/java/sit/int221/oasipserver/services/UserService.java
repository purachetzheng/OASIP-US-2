package sit.int221.oasipserver.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import sit.int221.oasipserver.dtos.CreateUserDto;
import sit.int221.oasipserver.dtos.Role;
import sit.int221.oasipserver.dtos.UserDto;
import sit.int221.oasipserver.entities.User;
import sit.int221.oasipserver.exception.type.ApiNotFoundException;
import sit.int221.oasipserver.repo.UserRepository;
import sit.int221.oasipserver.utils.ListMapper;

import java.util.List;

@Service
public class UserService {
    @Autowired private UserRepository repository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private ListMapper listMapper;
    final private FieldError roleErrorObj = new FieldError("createUserDto",
            "role", "role must be 'student', 'admin', 'lecturer'");

    public List<UserDto> getAll() {
        List<User> userList = repository.findAllByOrderByNameAsc();
        return listMapper.mapList(userList, UserDto.class, modelMapper);
    }

    public User getById(Integer id) {
        User user = repository.findById(id).orElseThrow
                (() -> new ApiNotFoundException("User id " + id + "Does Not Exist !!!"));
        return user;
    }

    public UserDto create(CreateUserDto newUser, BindingResult result) throws MethodArgumentNotValidException {
        User user = modelMapper.map(newUser, User.class);
        return modelMapper.map(repository.saveAndFlush(user), UserDto.class);
    }
}
