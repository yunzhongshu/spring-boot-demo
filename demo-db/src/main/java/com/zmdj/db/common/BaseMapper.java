package com.zmdj.db.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author zhangyunyun create on 2019/4/15
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
