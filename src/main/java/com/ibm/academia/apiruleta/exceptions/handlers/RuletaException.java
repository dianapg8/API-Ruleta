package com.ibm.academia.apiruleta.exceptions.handlers;

import com.ibm.academia.apiruleta.exceptions.ApuestaCancelada;
import com.ibm.academia.apiruleta.exceptions.DatosInvalidos;
import com.ibm.academia.apiruleta.exceptions.NoExisteRuleta;
import com.ibm.academia.apiruleta.exceptions.RuletaCerrada;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RuletaException
{
    @ExceptionHandler(value = ApuestaCancelada.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> apuestasCanceladas(ApuestaCancelada ac){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ac.getMessage());
        return response;
    }

    @ExceptionHandler(value = DatosInvalidos.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> datosInvalidos (DatosInvalidos di){
        Map<String, Object> response = new HashMap<>();
        response.put("message", di.getMessage());
        return response;
    }

    @ExceptionHandler(value = NoExisteRuleta.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> noExisteRuleta(NoExisteRuleta ne){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ne.getMessage());
        return response;
    }

    @ExceptionHandler(value = RuletaCerrada.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> ruletaCerrada(RuletaCerrada rc){
        Map<String, Object> response = new HashMap<>();
        response.put("message", rc.getMessage());
        return response;
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> missingRequestParameters(MissingServletRequestParameterException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", "Faltan los siguientes parámetros");
        response.put("param", ex.getParameterName());
        response.put("paramType", ex.getParameterType());
        return response;
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> paramTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", "Tipo de dato incorrecto");
        response.put("param", ex.getName());
        response.put("paramTypeExpected", ex.getParameter().getParameterType());
        response.put("paramTypeGot", ex.getValue().getClass().getName());
        return response;
    }
}