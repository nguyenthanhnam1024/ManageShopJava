package com.example.manage_shops.controller;

import com.example.manage_shops.entity.Order;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Commons commons;

    @GetMapping("/getAll/{idShop}")
    public ResponseEntity<?> getAllOrderByIdShop(HttpServletRequest httpServletRequest, @PathVariable int idShop) throws MyValidateException {
        return ResponseEntity.ok(orderService.getAllOrderByIdShop(httpServletRequest, idShop));
    }

    @GetMapping("/getById/{idShop}/{idOrder}")
    public ResponseEntity<?> getOrderById(HttpServletRequest httpServletRequest, @PathVariable int idShop, @PathVariable long idOrder) throws MyValidateException {
        return ResponseEntity.ok(orderService.getOrderById(httpServletRequest, idShop, idOrder));
    }

    @PostMapping("/save/{idShop}")
    public ResponseEntity<?> saveOrder(HttpServletRequest httpServletRequest, @PathVariable int idShop,@Valid @RequestBody Order order, BindingResult result) throws MyValidateException {
        Map<String, String> mapError = new HashMap<>();
        if (order.getIdProduct() < 1) {
            mapError.put("product", "please choose again product");
        }
        if (result.hasErrors() || !mapError.isEmpty()) {
            Map<String, String> mapBindingError = commons.handleExceptionInBindingResult(result);
            mapBindingError.putAll(mapError);
            return ResponseEntity.status(1000).body(mapBindingError);
        }
        return ResponseEntity.ok(orderService.saveOrder(httpServletRequest,idShop, order));
    }

    @PutMapping("/update/{idShop}")
    public ResponseEntity<?> updateOrder(HttpServletRequest httpServletRequest, @PathVariable int idShop, @Valid @RequestBody Order order, BindingResult result) throws MyValidateException {
        Map<String, String> mapError = new HashMap<>();
        if (order.getIdProduct() < 1) {
            mapError.put("product", "please choose again product");
        }
        if (result.hasErrors() || !mapError.isEmpty()) {
            Map<String, String> mapBindingError = commons.handleExceptionInBindingResult(result);
            mapBindingError.putAll(mapError);
            return ResponseEntity.status(1000).body(mapBindingError);
        }
        return ResponseEntity.ok(orderService.updateOrder(httpServletRequest,idShop, order));
    }

    @DeleteMapping("/delete/{idShop}/{idOrder}")
    public ResponseEntity<?> deleteOrder(HttpServletRequest httpServletRequest, @PathVariable int idShop, @PathVariable long idOrder) throws MyValidateException {
        orderService.deleteOrder(httpServletRequest, idShop, idOrder);
        return ResponseEntity.ok().build();
    }

    @GetMapping("search/{idShop}/{date}")
    public ResponseEntity<?> searchOrder(HttpServletRequest httpServletRequest, @PathVariable int idShop, @PathVariable String date) throws MyValidateException {
        return ResponseEntity.ok(orderService.searchOrderByDate(httpServletRequest, idShop, LocalDate.parse(date)));
    }
}
