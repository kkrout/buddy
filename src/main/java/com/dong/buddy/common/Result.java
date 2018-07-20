package com.dong.buddy.common;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class Result implements Serializable {

    private String status;

    private Object result;

    public Result(String status,Object result){
        this.status=status;
        this.result=result;
    }


    public Result(Object result){
        this.status="OK";
        this.result=result;
    }

    public Result(){
        this.status="OK";
    }

    public static Result SUCCESS(){
        return new Result("OK");
    }

}
