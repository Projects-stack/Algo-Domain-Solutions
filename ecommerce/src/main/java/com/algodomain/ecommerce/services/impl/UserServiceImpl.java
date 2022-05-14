package com.algodomain.ecommerce.services.impl;

import com.algodomain.ecommerce.entities.Product;
import com.algodomain.ecommerce.entities.User;
import com.algodomain.ecommerce.exceptions.ResourceNotFoundException;
import com.algodomain.ecommerce.payload.ProductDto;
import com.algodomain.ecommerce.payload.UserDto;
import com.algodomain.ecommerce.repositories.UserRepository;
import com.algodomain.ecommerce.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = this.userRepository.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return this.userToDto(user);
    }

    @Override
    public List<ProductDto> getCartItems(Integer userId) {
        try {
            List<Product> products = this.userRepository.getCartItems(userId);
            return products.stream().map((product) -> this.modelMapper.map(product, ProductDto.class))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User updatedUser = this.userRepository.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        this.userRepository.delete(user);
    }

    private User dtoToUser(UserDto userDto) {
        if(userDto == null)
            return null;

        return this.modelMapper.map(userDto, User.class);
    }

    private UserDto userToDto(User user) {
        if(user == null)
            return null;

        return this.modelMapper.map(user, UserDto.class);
    }
}
