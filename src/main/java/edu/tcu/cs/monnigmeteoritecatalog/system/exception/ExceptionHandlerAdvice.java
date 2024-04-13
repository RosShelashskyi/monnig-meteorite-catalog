package edu.tcu.cs.monnigmeteoritecatalog.system.exception;

import edu.tcu.cs.monnigmeteoritecatalog.sample.SampleNotFoundException;
import edu.tcu.cs.monnigmeteoritecatalog.system.Result;
import edu.tcu.cs.monnigmeteoritecatalog.system.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(SampleNotFoundException.class)
    @ResponseStatus(HttpStatus.FOUND)
    Result handleSampleNotFoundException(SampleNotFoundException ex) {
        return new Result(false, StatusCode.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Result handleObjectNotFoundException(ObjectNotFoundException ex){
        return new Result(false, StatusCode.NOT_FOUND, ex.getMessage());
    }
}
