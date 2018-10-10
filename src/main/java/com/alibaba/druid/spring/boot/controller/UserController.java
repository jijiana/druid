package com.alibaba.druid.spring.boot.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.alibaba.druid.spring.boot.entity.User;
import com.alibaba.druid.spring.boot.service.IUserService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {     
	@Autowired
	IUserService service;
	@GetMapping("/insertOne")    
	public Mono<String> insertOne(){   
		User n = new User();
		n.setName("name" );
		n.setEmail("email" );
		n.setDescription(
				"setDescriptionsetDescriptionsetDescriptionsetDescriptionsetDescriptionsetDescriptionsetDescriptionsetDescriptionsetDescriptionsetDescriptionsetDescription"
						);
		n.setAddress(
				"addressaddressaddressaddressaddressaddressaddressaddressaddressaddressaddressaddressaddressaddressaddressaddressaddress"
						);
		n.setLastUpdate(new Date().toString());
		n.setSex("" );
		n.setCount(1l);
		
		service.saveOnce(n);
		return Mono.just("insertOne finish");    
	}
	
	@GetMapping("/checktransaction")    
	public Mono<String> checktransaction(@RequestParam("pid") String pid){   
		System.out.println(pid);
		Long id = Long.valueOf(pid);
		try{
			service.checkTransactional(id);
		}catch(Exception e){
			e.printStackTrace();
			return Mono.error(new RuntimeException("test checkTransaction error"));
		}
		return Mono.just("checktransaction finish");    
	}
	
	@GetMapping("/continue")    
	public Mono<String> insertContinue(){   
		service.register();
		return Mono.just("insertContinue finish");    
	}
}