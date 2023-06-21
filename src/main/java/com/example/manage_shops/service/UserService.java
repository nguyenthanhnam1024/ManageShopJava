package com.example.manage_shops.service;

import com.example.manage_shops.entity.User;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.request.RequestUpdateUser;
import com.example.manage_shops.request.RequestUser;
import com.example.manage_shops.response.ResponseLogin;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
     List<User> getAllUser(HttpServletRequest httpServletRequest, int idShop) throws MyValidateException;

     RequestUser getById(HttpServletRequest httpServletRequest, long idUser) throws MyValidateException;

     void saveUserFromADMIN(HttpServletRequest httpServletRequest, RequestUser requestUser) throws MyValidateException;

     ResponseLogin updateUser(HttpServletRequest httpServletRequest, RequestUpdateUser requestUpdateUser) throws MyValidateException;

     User updateUserFromADMIN(HttpServletRequest httpServletRequest, RequestUser requestUser) throws MyValidateException;

     List<User> searchUserByKeyword(HttpServletRequest httpServletRequest, String keyword, String roleName) throws MyValidateException;

     List<User> searchUserByHQL(HttpServletRequest httpServletRequest, String keyword, String roleName) throws MyValidateException;

     void deleteUser(HttpServletRequest httpServletRequest, Long idUser) throws MyValidateException;
}
