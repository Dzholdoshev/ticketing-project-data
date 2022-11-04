package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import com.cydeo.mapper.RoleMapper;
import com.cydeo.repository.RoleRepository;
import com.cydeo.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService  {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper, ModelMapper roleMapper, RoleMapper roleMapper1, RoleMapper roleMapper2) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper2;
    }

    @Override
    public List<RoleDTO> listAllRoles() {


        //Controller called me and requesting all RoleDTOs so it can show in the drop-down in the UI
        //I need to make a call to db and get all the roles from table
        //go to repository and find a service which gives me the roles from db
        //how i will call any service here?

       List<Role> roleList =  roleRepository.findAll();

       return roleList.stream()
               .map(roleMapper::convertToDto)
               .collect(Collectors.toList());

    }

    @Override
    public RoleDTO findById(Long id) {
        return  roleMapper.convertToDto(roleRepository.findById(id).get());
    }
}
