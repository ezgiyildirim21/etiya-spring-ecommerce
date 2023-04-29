package com.etiya.ecommerce;

import com.etiya.ecommerce.core.exception.ServiceException;
import com.etiya.ecommerce.core.utils.result.DataResult;
import com.etiya.ecommerce.core.utils.result.ErrorDataResult;
import com.etiya.ecommerce.core.utils.result.ErrorResult;
import com.etiya.ecommerce.core.utils.result.Result;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.*;

@SpringBootApplication
@RestControllerAdvice
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}


	@Bean
	public ResourceBundleMessageSource bundleMessageSource(){
		ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
		messageSource.setBasename("message");
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver(){
		AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
		acceptHeaderLocaleResolver.setDefaultLocale(new Locale("tr"));
		return acceptHeaderLocaleResolver;
	}

	@Bean
	public ModelMapper forResponse() { return new ModelMapper();}



	@ExceptionHandler({ServiceException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Result handleBusinessException(ServiceException exception)
	{

		return new ErrorResult(exception.getMessage());
	}


	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<ErrorResult,ErrorResult>handleValidationException(MethodArgumentNotValidException exception)
	{
		 Map<ErrorResult,ErrorResult>errors = new HashMap<>();


		for(FieldError fieldError : exception.getBindingResult().getFieldErrors()){

			ErrorResult errorResult=new ErrorResult(fieldError.getDefaultMessage());
			ErrorResult errorResult1=new ErrorResult(fieldError.getField());

			errors.put(errorResult1,errorResult);
		}

		return errors;
	}

}
