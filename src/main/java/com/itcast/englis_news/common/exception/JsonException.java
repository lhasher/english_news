package com.itcast.englis_news.common.exception;

import com.itcast.englis_news.common.result.ResultCodeEnum;
import lombok.Getter;

/**
 * <p>
 * JSON异常
 * </p>
 *
 * @package: com.xkcoding.exception.handler.exception
 * @description: JSON异常
 * @author: yangkai.shen
 * @date: Created in 2018/10/2 9:18 PM
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Getter
public class JsonException extends BaseException {

	public JsonException(ResultCodeEnum status) {
		super(status);
	}

	public JsonException(Integer code, String message) {
		super(code, message);
	}
}
