package com.chixing.controller;

import com.chixing.commen.ServerResponse;
import com.chixing.entity.Customer;
import com.chixing.entity.CustomerTokenDTO;
import com.chixing.service.impl.CustomerServiceImpl;
import com.chixing.util.TokenUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-10-19
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    private CustomerServiceImpl customerService;
//  如果不用这个构造器，上面这个customerService就是灰色的
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ServerResponse login(Customer customer, HttpServletResponse response, HttpServletRequest request){
        //调用服务层的方法
        ServerResponse result=customerService.login(customer);
        if (result.getResultCode()==200){
            System.out.println("登录成功！");
        }
        return result;
    }

    @GetMapping("test1")
    @ResponseBody
    public String test1(HttpServletRequest request){
//        从头部请求获得token
        String token=request.getHeader("token");
        System.out.println(token);
//        解析token，获得登录用户的信息
        CustomerTokenDTO user = TokenUtil.parseToken(token);
        String customerId = user.getCustId();
        System.out.println("test1 customer Id = "+customerId);
        return "test1 get token ok";
    }
}
