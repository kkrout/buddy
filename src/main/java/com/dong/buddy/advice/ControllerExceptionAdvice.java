package com.dong.buddy.advice;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dong.buddy.exception.ExceptionCode;
import com.dong.buddy.exception.ExceptionResult;
import com.dong.buddy.exception.ICommonExpcetion;

@ControllerAdvice
public class ControllerExceptionAdvice
{

    private static Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * 
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * 
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model)
    {
        model.addAttribute("author", "Magical Sam");
    }

    /**
     * 全局异常捕捉处理
     * 
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ExceptionResult errorHandler(Exception ex, HttpServletResponse reponse)
    {
        reponse.setStatus(500);
        if (ex instanceof ICommonExpcetion)
        {
            ICommonExpcetion comEx = (ICommonExpcetion) ex;
            logger.error("全局异常捕捉处理", ex);
            return new ExceptionResult(comEx.getErrorMsg(), comEx.getErrorCode());
        }
        logger.error("全局异常捕捉处理", ex);
        return new ExceptionResult(ex.getMessage(), ExceptionCode.SYSTEM);
    }
}
