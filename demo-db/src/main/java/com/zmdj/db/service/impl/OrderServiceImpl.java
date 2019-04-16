package com.zmdj.db.service.impl;

import com.zmdj.db.dao.domain.Order;
import com.zmdj.db.dao.mapper.OrderMapper;
import com.zmdj.db.service.OrderService;

import org.springframework.stereotype.Service;

import java.util.Date;

import javax.annotation.Resource;

/**
 * @author zhangyunyun create on 2019/4/15
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public boolean insert(Order order) {

        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setStatus("wait_pay");

        return orderMapper.insertSelective(order) > 0;
    }
}
