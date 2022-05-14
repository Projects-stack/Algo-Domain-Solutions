package com.algodomain.ecommerce.services;

import com.algodomain.ecommerce.payload.ProductDto;
import com.algodomain.ecommerce.payload.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(Integer id);

    UserDto updateUser(UserDto userDto, Integer id);

    void deleteUser(Integer id);

    List<ProductDto> getCartItems(Integer userId);
}
