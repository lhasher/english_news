package com.itcast.englis_news.common.handler;

import com.itcast.englis_news.common.exception.BaseException;
import com.itcast.englis_news.common.exception.JsonException;
import com.itcast.englis_news.common.exception.PageException;
import com.itcast.englis_news.common.result.Result;
import com.itcast.englis_news.common.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理器
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 通用异常处理
     * @param e 通用异常
     * @return 异常结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        log.error("[Exception]:{}",e.getMessage());
        return Result.error();
    }

    /**
     * 空指针异常处理
     * @param e 空指针异常
     * @return 异常结果
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result error(NullPointerException e) {
        log.error("[NullPointerException]:{}",e.getMessage());
        return Result.setResult(ResultCodeEnum.NULL_POINT_ERROR);
    }

    /**
     * 自定义异常处理
     * @param e 自定义异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public Result error(BaseException e) {
        log.error("[BaseException]:{}",e.getMessage());
        return Result.error().message(e.getMessage()).code(e.getCode());
    }

    /**
     * JsonException异常处理
     * @param e JsonException
     * @return 异常结果
     */
    @ExceptionHandler(value = JsonException.class)
    @ResponseBody
    public Result error(JsonException e) {
        log.error("[JsonException]:{}",e.getMessage());
        return Result.error().message(e.getMessage()).code(e.getCode());
    }

    /**
     * PageException异常处理
     * @param e PageException
     * @return 异常结果
     */
    @ExceptionHandler(value = PageException.class)
    @ResponseBody
    public Result error(PageException e) {
        log.error("[PageException]:{}",e.getMessage());
        return Result.error().message(e.getMessage()).code(e.getCode());
    }

}
