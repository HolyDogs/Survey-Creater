package com.me.beans;

import java.io.Serializable;
import java.util.HashMap;

public class ReturnMessage implements Serializable {
    public Boolean success;
    public Boolean rSuccess;
    public String data;
    public int res_code;
    public HashMap map;
    public Page page;
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

    public ReturnMessage(Boolean flag,int res_code){
        this.success = flag;
        this.res_code = res_code;
    }

    public ReturnMessage(Boolean flag,HashMap hashMap){
        this.success = true;
        this.map = hashMap;
    }

    public ReturnMessage(Boolean flag,Page page){
        this.success = true;
        this.page = page;
    }


    @Override
    public String toString() {
        return "ReturnMessage{" +
                "success=" + success +
                ", rSuccess=" + rSuccess +
                ", data='" + data + '\'' +
                ", res_code=" + res_code +
                '}';
    }
}
