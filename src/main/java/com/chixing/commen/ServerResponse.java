package com.chixing.commen;

import lombok.Data;

@Data
//让postman里面的地址能找到这里的数据
public class ServerResponse {
    private int resultCode;
    private String resultReason;
    private Object data;

    public ServerResponse(){}
    public ServerResponse(int resultCode, String resultReason, Object data) {
        this.resultCode = resultCode;
        this.resultReason = resultReason;
        this.data = data;
    }
    public static ServerResponse sucsess(String resultReason, Object data){
        ServerResponse response=new ServerResponse(200,resultReason,data);
        return response;
    }
    public static ServerResponse filed(String resultReason, Object data){
        ServerResponse response=new ServerResponse(201,resultReason,data);
        return response;
    }
}
