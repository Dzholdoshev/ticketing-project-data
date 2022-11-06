package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public List<UserDTO> listAllUsers() {

   List<User> userList = userRepository.findAll(Sort.by("firstName"));

        return userList.stream()
                .map(obj->userMapper.convertToUserDto(obj))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        return userMapper.convertToUserDto(userRepository.findByUserName(username));
    }

    @Override
    public void save(UserDTO user) {
       userRepository.save(userMapper.convertToEntity(user));

    }

    @Override
    public void deleteByUserName(String username) {

        userRepository.deleteByUserName(username);
    }

    @Override
    public void delete(String username) {
        //go to db and get that user with user name
        //change the isdeleted filed to true
        //save the object in th db
       User user = userRepository.findByUserName(username);
       user.setIsDeleted(true);
       userRepository.save(user);


    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User> users = userRepository.findByRoleDescriptionIgnoreCase(role);

      return   users.stream()
                .map(userMapper::convertToUserDto)
                .collect(Collectors.toList());


    }

    @Override
    public UserDTO update(UserDTO userDTO) { //

        //Find current user
        User user = userRepository.findByUserName(userDTO.getUserName()); //has id
        //Map update user dto to entity object
        User convertedUser= userMapper.convertToEntity(userDTO);//has id?

        //set id to the converted object
        convertedUser.setId(user.getId());
        //save the updated user in the db
        userRepository.save(convertedUser);
        return findByUserName(user.getUserName());
    }


}
