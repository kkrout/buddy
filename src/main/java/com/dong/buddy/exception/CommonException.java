package com.dong.buddy.exception;

public class CommonException extends Exception implements ICommonExpcetion
{

    private String errorCode;

    private String errorMsg;
    /**
     * [简要描述]:
     * 
     * @author zhdong
     */
    private static final long serialVersionUID = 5762424501632173625L;

    public CommonException(String errorCode)
    {
        this(errorCode, errorCode);
    }

    public CommonException(String errorMsg, String errorCode)
    {
        super(errorMsg);
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorCode()
    {
        return errorCode;
    }

    @Override
    public String getErrorMsg()
    {
        return errorMsg;
    }

}
