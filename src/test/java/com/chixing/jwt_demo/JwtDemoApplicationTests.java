package com.chixing.jwt_demo;

import com.chixing.commen.ServerResponse;
import com.chixing.entity.Customer;
import com.chixing.entity.CustomerTokenDTO;
import com.chixing.mapper.CustomerMapper;
import com.chixing.service.ICustomerService;
import com.chixing.util.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtDemoApplicationTests {
    @Autowired
    public CustomerMapper customerMapper;
    @Autowired
    public ICustomerService customerService;
    @Test
    public void test(){
        Customer customer1=customerMapper.selectById("101");
        System.out.println(customer1.getCustName());
    }
    @Test
    public void testlogin(){
        Customer customer = new Customer();
        customer.setCustName("张三");
        customer.setCustPassword("aaa");
        ServerResponse login = customerService.login(customer);
        String token = login.getData().toString();
        System.out.println("测试："+token);
        CustomerTokenDTO customerTokenDTO = TokenUtil.parseToken(token);
        System.out.println(customerTokenDTO);
    }
}
