package com.me.beans;

import java.io.Serializable;

public class ReturnMessage implements Serializable {
    public Boolean success;
    public Boolean rSuccess;
    public String data;
    public ReturnMessage(Boolean flag){
        this.success=flag;
    }

    public ReturnMessage(Boolean flag, Boolean rflag){
        this.success = flag;
        this.rSuccess = rflag;
    }

    public ReturnMessage(Boolean flag,String data){
        this.success = flag;
        this.data = data;
    }
}
