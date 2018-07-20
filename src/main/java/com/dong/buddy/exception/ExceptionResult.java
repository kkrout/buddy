package com.dong.buddy.exception;

import java.io.Serializable;

public class ExceptionResult implements Serializable
{

    public ExceptionResult(String errorMsg, String errorCode)
    {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    /**
     * [简要描述]:
     * 
     * @author zhdong
     */
    private static final long serialVersionUID = 1L;

    private String errorCode;

    private String errorMsg;

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

}
