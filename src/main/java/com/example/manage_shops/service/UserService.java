package com.example.manage_shops.service;

import com.example.manage_shops.entity.User;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.request.RequestUpdateUser;
import com.example.manage_shops.request.RequestUser;
import com.example.manage_shops.response.ResponseLogin;

import java.util.List;
import java.util.Map;

public interface UserService {
     List<User> getAllUser(ResponseLogin responseLogin) throws MyValidateException;

     Map<String, String> errorCheckRequestRegisterFromADMINMap(RequestUser requestUser);

     void saveUserFromADMIN(RequestUser requestUser) throws MyValidateException;

     List<String> getListRoleName();

     ResponseLogin updateUser(RequestUpdateUser requestUpdateUser) throws MyValidateException;

     List<User> searchUserByKeyword(String keyword, ResponseLogin responseLogin) throws MyValidateException;
}
