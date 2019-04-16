package com.zmdj.db.controller;

import com.zmdj.db.controller.req.OrderRequest;
import com.zmdj.db.controller.res.Result;
import com.zmdj.db.dao.domain.Order;
import com.zmdj.db.service.OrderService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangyunyun create on 2019/4/15
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    public Result<Boolean> insertOrder(@RequestBody OrderRequest orderRequest) {

        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setProductId(orderRequest.getProductId());
        order.setPrice(orderRequest.getPrice());
        order.setTitle(orderRequest.getTitle());
        Result<Boolean> result = new Result<>();

        return result.succeed(orderService.insert(order));
    }

}
