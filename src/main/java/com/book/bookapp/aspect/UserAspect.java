package com.book.bookapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.book.bookapp.dto.request.UserAddDeleteBookRequest;
import com.book.bookapp.dto.request.UserRequest;
import com.book.bookapp.dto.response.AppResponse;
import com.book.bookapp.repository.BookRepository;
import com.book.bookapp.repository.UserRepository;

@Aspect
@Component
public class UserAspect {
	
	 	@Autowired
	    private UserRepository repository;

	    @Autowired
	    private BookRepository bookRepository;
	    
	    @Pointcut("execution(* com.book.bookapp.service.impl.UserServiceImpl.createUser(..))")
	    private void createUser() {};
	    
	    @Pointcut("execution(* com.book.bookapp.service.impl.UserServiceImpl.*(..))")
	    private void userFuncts() {};
	    
	    @Around("createUser()")
	    public Object checkCreateUser(ProceedingJoinPoint theJoinPoint) throws Throwable{
	    	Object[] args = theJoinPoint.getArgs();
	    	
	    	UserRequest request = (UserRequest) args[0];
	    	
	    	if(repository.findUserByEmail(request.getEmail()) != null) {
	    		return AppResponse.builder()
						.status("Fail")
						.responseMessage("User Allready exists!")
						.build();
	    	}
	    	
	    	return theJoinPoint.proceed();
	    }

	    @Around("userFuncts() && !createUser()")
	    public Object checkUserAdvice(ProceedingJoinPoint theJoinPoint) throws Throwable {
	        Object[] args = theJoinPoint.getArgs();

	        if (args.length == 0) {
	            return buildFailResponse("No arguments provided!");
	        }

	        Object arg = args[0];

	        if (arg instanceof UserRequest) {
	            return handleUserRequest(theJoinPoint, (UserRequest) arg);
	        } else if (arg instanceof UserAddDeleteBookRequest) {
	            return handleUserAddDeleteBookRequest(theJoinPoint, (UserAddDeleteBookRequest) arg);
	        } else if (arg instanceof String) {
	            return handleStringRequest(theJoinPoint, (String) arg);
	        } else {
	            return buildFailResponse("Unknown request parameter!");
	        }
	    }

	    private Object handleUserRequest(ProceedingJoinPoint theJoinPoint, UserRequest request) throws Throwable {
	        if (repository.findUserByEmail(request.getEmail()) == null) {
	            return buildFailResponse("User Not Found!!");
	        } else {
	            return theJoinPoint.proceed();
	        }
	    }

	    private Object handleUserAddDeleteBookRequest(ProceedingJoinPoint theJoinPoint, UserAddDeleteBookRequest request) throws Throwable {
	        if (repository.findUserByEmail(request.getUserEmail()) == null || bookRepository.findByName(request.getBookName()) == null) {
	            return buildFailResponse("User or Book Not Found!");
	        } else {
	            return theJoinPoint.proceed();
	        }
	    }

	    private Object handleStringRequest(ProceedingJoinPoint theJoinPoint, String email) throws Throwable {
	        if (repository.findUserByEmail(email) == null) {
	            return null;
	        } else {
	            return theJoinPoint.proceed();
	        }
	    }

	    private AppResponse buildFailResponse(String message) {
	        return AppResponse.builder()
	                .status("Fail")
	                .responseMessage(message)
	                .build();
	    }

}
