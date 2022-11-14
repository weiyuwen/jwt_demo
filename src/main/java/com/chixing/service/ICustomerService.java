package com.chixing.service;

import com.chixing.commen.ServerResponse;
import com.chixing.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-10-19
 */
public interface ICustomerService  {
    ServerResponse login(Customer customer);
}
