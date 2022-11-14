package com.chixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2022-10-19
 */
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    //用于注解主键
    @TableId
    private String custId;

    private String custName;

    private String custPassword;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
    public String getCustPassword() {
        return custPassword;
    }

    public void setCustPassword(String custPassword) {
        this.custPassword = custPassword;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "custId=" + custId +
            ", custName=" + custName +
            ", custPassword=" + custPassword +
        "}";
    }
}
