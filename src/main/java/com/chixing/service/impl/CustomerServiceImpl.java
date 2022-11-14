package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.commen.ServerResponse;
import com.chixing.entity.Customer;
import com.chixing.entity.CustomerTokenDTO;
import com.chixing.mapper.CustomerMapper;
import com.chixing.service.ICustomerService;
import com.chixing.util.TokenUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-10-19
 */
@Service
public class CustomerServiceImpl implements ICustomerService {
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public ServerResponse login(Customer customer) {
        QueryWrapper<Customer> wrapper=new QueryWrapper<>();
                   //这个是数据库里面的字段名，和页面输入的绑定起来
        wrapper.eq("cust_name",customer.getCustName());
        wrapper.eq("cust_password",customer.getCustPassword());

        Customer loginCustomer=customerMapper.selectOne(wrapper);
        System.out.println("查询到登录的账户："+loginCustomer);

        if (loginCustomer!=null){
            CustomerTokenDTO customerTokenDTO=new CustomerTokenDTO(loginCustomer.getCustId(),loginCustomer.getCustName());
            String token= TokenUtil.sign(customerTokenDTO);
            System.out.println("service token: "+token);
            return ServerResponse.sucsess("登录成功：",token);
        }else {
            return ServerResponse.filed("登录失败：",null);
        }
    }
}
