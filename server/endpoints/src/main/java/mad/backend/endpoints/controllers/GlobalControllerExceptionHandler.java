package mad.backend.endpoints.controllers;

import mad.backend.endpoints.exceptions.UserDoesNotExistException;
import mad.backend.endpoints.exceptions.WrongPasswordException;
import mad.backend.endpoints.exceptions.WrongUserTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User does not exist")
	@ExceptionHandler(UserDoesNotExistException.class)
	public void handleUserDoesNotExistException() {
	}

	@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Wrong password")
	@ExceptionHandler(WrongPasswordException.class)
	public void handleWrongPasswordException() {
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Wrong user type")
	@ExceptionHandler(WrongUserTypeException.class)
	public void handleWrongUserTypeException() {
	}
}
