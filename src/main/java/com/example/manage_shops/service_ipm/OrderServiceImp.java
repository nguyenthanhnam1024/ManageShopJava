package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.Order;
import com.example.manage_shops.entity.Product;
import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.entity.User;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.jwt.ExtractDataFromJwt;
import com.example.manage_shops.my_enum.RoleEnum;
import com.example.manage_shops.repository.OrderRepo;
import com.example.manage_shops.repository.ProductRepo;
import com.example.manage_shops.repository.ShopRepo;
import com.example.manage_shops.repository.UserRepo;
import com.example.manage_shops.response.ResponseOrder;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService {
    private final OrderRepo orderRepo;
    private final Commons commons;
    private final ExtractDataFromJwt extractDataFromJwt;
    private final ShopRepo shopRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    @Override
    public List<ResponseOrder> getAllOrderByIdShop(HttpServletRequest httpServletRequest, int idShop) throws MyValidateException {
        commons.validateRole(httpServletRequest, Arrays.asList(RoleEnum.ADMIN.getRoleName(), RoleEnum.MANAGE.getRoleName(), RoleEnum.STAFF.getRoleName()));
        commons.validateShopForUserToQuery(idShop, extractDataFromJwt.extractIdShopFromJwt(httpServletRequest));
        try {
            return this.mapListOrderInfoListResponseOrder(orderRepo.findAllByIdShop(idShop));
        } catch (Exception ex) {
            throw new MyValidateException("get orders failure");
        }
    }

    @Override
    public ResponseOrder getOrderById(HttpServletRequest httpServletRequest, int idShop, Long idOrder) throws MyValidateException {
        commons.validateRole(httpServletRequest, Arrays.asList(RoleEnum.ADMIN.getRoleName(), RoleEnum.MANAGE.getRoleName(), RoleEnum.STAFF.getRoleName()));
        commons.validateShopForUserToQuery(idShop, extractDataFromJwt.extractIdShopFromJwt(httpServletRequest));
        Optional<Order> optionalOrder = orderRepo.findById(idOrder);
        if (optionalOrder.isPresent()) {
            return this.mapOrderInfoResponseOrder(optionalOrder.get());
        }
        throw new MyValidateException("get order failure");
    }

    @Override
    @Transactional
    public ResponseOrder saveOrder(HttpServletRequest httpServletRequest, int idShop, Order order) throws MyValidateException {
        commons.validateRole(httpServletRequest, Arrays.asList(RoleEnum.ADMIN.getRoleName(), RoleEnum.MANAGE.getRoleName(), RoleEnum.STAFF.getRoleName()));
        commons.validateShopForUserToQuery(idShop, extractDataFromJwt.extractIdShopFromJwt(httpServletRequest));
        if (order.getId() != 0) {
            throw new MyValidateException("request invalid");
        }
        Optional<Shop> optionalShop = shopRepo.findById(order.getIdShop());
        if (!optionalShop.isPresent()) {
            throw new MyValidateException("shop of you no exist");
        }
        Optional<User> optionalUser = userRepo.findById(order.getIdSeller());
        if (!optionalUser.isPresent()) {
            throw new MyValidateException("seller unknown");
        }
        if (!extractDataFromJwt.extractUserFromJwt(httpServletRequest).getName().equals(optionalUser.get().getName())) {
            throw new MyValidateException("request invalid");
        }
        Optional<Product> optionalProduct = productRepo.findById(order.getIdProduct());
        if (!optionalProduct.isPresent()) {
            throw new MyValidateException("product of you no exist");
        }
        try {
            return this.mapOrderInfoResponseOrder(orderRepo.save(order));
        }catch (Exception ex) {
            throw new MyValidateException("save failure");
        }
    }

    @Override
    @Transactional
    public ResponseOrder updateOrder(HttpServletRequest httpServletRequest, int idShop, Order order) throws MyValidateException {
        commons.validateRole(httpServletRequest, Arrays.asList(RoleEnum.ADMIN.getRoleName(), RoleEnum.MANAGE.getRoleName(), RoleEnum.STAFF.getRoleName()));
        commons.validateShopForUserToQuery(idShop, extractDataFromJwt.extractIdShopFromJwt(httpServletRequest));
        Optional<Order> optionalOrder = orderRepo.findById(order.getId());
        if (!optionalOrder.isPresent()) {
            throw new MyValidateException("order no exist for update");
        }
        Optional<Shop> optionalShop = shopRepo.findById(order.getIdShop());
        if (!optionalShop.isPresent()) {
            throw new MyValidateException("shop of you no exist");
        }
        Optional<User> optionalUser = userRepo.findById(order.getIdSeller());
        if (!optionalUser.isPresent()) {
            throw new MyValidateException("seller unknown");
        }
        if (!extractDataFromJwt.extractUserFromJwt(httpServletRequest).getName().equals(optionalUser.get().getName()) && extractDataFromJwt.extractRoleNamesFromJwt(httpServletRequest).contains(RoleEnum.STAFF.getRoleName())) {
            throw new MyValidateException("only edit your bill");
        }
        Optional<Product> optionalProduct = productRepo.findById(order.getIdProduct());
        if (!optionalProduct.isPresent()) {
            throw new MyValidateException("product of you no exist");
        }
        try {
            return this.mapOrderInfoResponseOrder(orderRepo.save(order));
        }catch (Exception ex) {
            throw new MyValidateException("update failure");
        }
    }

    @Override
    @Transactional
    public void deleteOrder(HttpServletRequest httpServletRequest, int idShop, Long idOrder) throws MyValidateException {
        commons.validateRole(httpServletRequest, Arrays.asList(RoleEnum.ADMIN.getRoleName(), RoleEnum.MANAGE.getRoleName()));
        commons.validateShopForUserToQuery(idShop, extractDataFromJwt.extractIdShopFromJwt(httpServletRequest));
        Optional<Order> optionalOrder = orderRepo.findById(idOrder);
        if (!optionalOrder.isPresent()) {
            throw new MyValidateException("order no exist for delete");
        }
        try {
            orderRepo.deleteById(idOrder);
        } catch (Exception ex) {
            throw new MyValidateException("delete failure");
        }
    }

    @Override
    public List<ResponseOrder> searchOrderByDate(HttpServletRequest httpServletRequest, int idShop, LocalDate date) throws MyValidateException {
        commons.validateRole(httpServletRequest, Arrays.asList(RoleEnum.ADMIN.getRoleName(), RoleEnum.MANAGE.getRoleName(), RoleEnum.STAFF.getRoleName()));
        commons.validateShopForUserToQuery(idShop, extractDataFromJwt.extractIdShopFromJwt(httpServletRequest));
        try {
            return this.mapListOrderInfoListResponseOrder(orderRepo.findAllByDate(date));
        } catch (Exception ex) {
            throw new MyValidateException("search failure");
        }
    }

    @Override
    public ResponseOrder mapOrderInfoResponseOrder(Order order) throws MyValidateException {
        ModelMapper modelMapper = new ModelMapper();
        ResponseOrder responseOrder = modelMapper.map(order, ResponseOrder.class);
        responseOrder.setShop(shopRepo.findById(order.getIdShop()).orElseThrow(() -> new MyValidateException("shop invalid to return")));
        responseOrder.setSeller(userRepo.findById(order.getIdSeller()).orElseThrow(() -> new MyValidateException("seller invalid to return")));
        responseOrder.setProduct(productRepo.findById(order.getIdProduct()).orElseThrow(() -> new MyValidateException("product invalid to return")));
        return responseOrder;
    }

    @Override
    public List<ResponseOrder> mapListOrderInfoListResponseOrder(List<Order> orderList) throws MyValidateException {
        List<ResponseOrder> orders = new ArrayList<>();
        for (Order order : orderList) {
            orders.add(this.mapOrderInfoResponseOrder(order));
        }
        return orders;
    }
}
