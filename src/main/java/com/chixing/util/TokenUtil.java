package com.chixing.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.chixing.entity.CustomerTokenDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weiyuwen
 */
public class TokenUtil {

//    /**
//     * 密钥盐
//     */
//    private static final String TOKEN_SECREAT="112233AAbbcc";
//
//    /**
//     *
//     * @param customerTokenDTO 1
//     * @return 生成的token不带过期时间，因为它的过期时间由redis来管理
//     */
//    public static String sign(CustomerTokenDTO customerTokenDTO){
//        //声明token
//        String token;
//        //声明一个map存放头部需要承载的两个信息
//        Map<String,Object> header=new HashMap<>(2);
//        //声明的类型：jwt
//        header.put("Type","jwt");
//        //声明加密的方式
//        header.put("alg","HS256");
//        //生成令牌的头部，赋值给token
//        token=JWT.create()
//                .withHeader(header)
////                存放数据
//                .withClaim("token", JSONObject.toJSONString(customerTokenDTO))
////              声明一个算法，使用的盐度为：112233AAbbcc
//                .sign(Algorithm.HMAC256(TOKEN_SECREAT));
//
//        return token;
//    }
//
//    /**
//     *
//     * @param token 1
//     * @return 解析好的token，即源码
//     */
//    public static CustomerTokenDTO parseToken(String token){
//        System.out.println(token);
//        //用什么方法加密的就用什么方法解密
//        Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECREAT);
//        //用什么类型加密的，就用什么类型来验证
//        JWTVerifier verifier =JWT.require(algorithm).build();
//        //验证者要使用验证方式
//        DecodedJWT decodedJWT =verifier.verify(token);
//        // 验证之后就要 破译（decoded）,破译之后得到的是String类型
//        String tokenInfo=decodedJWT.getClaim("token").asString();
//        System.out.println("tokenInfo 的内容是： "+tokenInfo);
//        // 把破译后的tokenInfo转换为json类型，然后赋值给customerTokenDTO，它只需要用户id和name
//        CustomerTokenDTO customerTokenDTO= JSON.parseObject(tokenInfo,CustomerTokenDTO.class);
//        System.out.println("获得token中的信息的是："+customerTokenDTO);
//
//        return customerTokenDTO;
//    }
//token到期时间60s
private static final long EXPIRE_TIME= 600*1000;
    //密钥盐
    private static final String TOKEN_SECRET="123456qwertyuiop789";

    /**
     * 创建一个token
     * @param customerTokenDTO
     * @return 生成的token中不带有过期时间，token的过期时间由redis进行管理
     */
    public static String sign(CustomerTokenDTO customerTokenDTO){
        System.out.println("sign customer:" + customerTokenDTO);
        String token=null;
        try {
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("token", JSONObject.toJSONString(customerTokenDTO))//存放数据
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException| JWTCreationException je) {
            je.printStackTrace();

        }
        return token;
    }
    /**
     * 对token进行验证
     * @param token
     * @return
     */
    public static CustomerTokenDTO parseToken(String token){

        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        String tokenInfo = decodedJWT.getClaim("token").asString();
        CustomerTokenDTO customerTokenDTO = JSON.parseObject(tokenInfo, CustomerTokenDTO.class);
        System.out.println("获得的token中的信息是：" + customerTokenDTO);
        return customerTokenDTO;
    }


}

