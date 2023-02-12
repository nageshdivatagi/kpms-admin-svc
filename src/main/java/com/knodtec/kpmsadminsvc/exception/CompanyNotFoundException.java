package com.knodtec.kpmsadminsvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CompanyNotFoundException   extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompanyNotFoundException(String message) {
		super(message);

	}
}
