package ie.nuigalway.sd3.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

	//custom exception handler
	@ExceptionHandler(ErrorResponseException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler( Exception ex) {

		ErrorResponse error = new ErrorResponse();
		error.setErrorCode( HttpStatus.INTERNAL_SERVER_ERROR.value() );
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
