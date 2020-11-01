package com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.beans.ResponseBean;
import com.app.constant.MessageConstant;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(CheckListAppException.class)
	public ResponseEntity<ResponseBean> matrimonyException(CheckListAppException e) {
		return new ResponseEntity<>(
				ResponseBean.builder().satusCode(e.getCode()).status(false).message(e.getMessage())
						.errorCode((-1 == e.getErrorCodeValue()) ? null : String.valueOf(e.getErrorCodeValue()))
						.hasError(Boolean.TRUE).messageDescription(e.getDeveloperMessage()).build(),
				HttpStatus.valueOf(e.getCode()));
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseBean handleGeneralException(final Exception e) {
		return ResponseBean.builder().satusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).status(false)
				.message(e.getMessage()).hasError(Boolean.TRUE)
				.messageDescription(
						null == e.getMessage() ? HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() : e.getMessage())
				.build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseBean handleValidationExceptions(MethodArgumentNotValidException ex) {
		StringBuffer sb = new StringBuffer();
		ex.getBindingResult().getAllErrors().get(0);
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			sb.append(fieldName + "-" + errorMessage + ",");
		});
		return ResponseBean.builder().message(MessageConstant.INVALID_REQUEST).messageDescription(sb.toString())
				.status(false).satusCode(HttpStatus.BAD_REQUEST.value()).hasError(true).build();
	}

}
