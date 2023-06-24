package com.example.manage_shops.service;

import com.example.manage_shops.entity.Order;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.response.ResponseOrder;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<ResponseOrder> getAllOrderByIdShop(HttpServletRequest httpServletRequest, int idShop) throws MyValidateException;

    ResponseOrder getOrderById(HttpServletRequest httpServletRequest, int idShop, Long idOrder) throws MyValidateException;

    ResponseOrder saveOrder(HttpServletRequest httpServletRequest, int idShop, Order order) throws MyValidateException;

    ResponseOrder updateOrder(HttpServletRequest httpServletRequest, int idShop, Order order) throws MyValidateException;

    void deleteOrder(HttpServletRequest httpServletRequest,int idShop, Long idOrder) throws MyValidateException;

    List<ResponseOrder> searchOrderByDate(HttpServletRequest httpServletRequest, int idShop, LocalDate date) throws MyValidateException;

    ResponseOrder mapOrderInfoResponseOrder(Order order) throws MyValidateException;

    List<ResponseOrder> mapListOrderInfoListResponseOrder(List<Order> orderList) throws MyValidateException;
}
