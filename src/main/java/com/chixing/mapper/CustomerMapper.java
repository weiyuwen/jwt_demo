package com.chixing.mapper;

import com.chixing.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2022-10-19
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

}

