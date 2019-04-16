package com.zmdj.db.controller.req;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author zhangyunyun create on 2019/4/15
 */
@Data
public class OrderRequest {

    private Long userId;

    private Long productId;

    private String title;

    private BigDecimal price;


}
