package com.examoe.securitydb2.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:9229/springbootwithsecurity/userlogin1
// http://localhost:9229/springbootwithsecurity/adminlogin

@RestController
public class SpringBootRestController {
	@RequestMapping("/userlogin1")
	public String userValidation1() {
		
		UserDetails user_details = (UserDetails)SecurityContextHolder
									.getContext()
									.getAuthentication().getPrincipal();

		return "User: Successfully logged in 1111!"+user_details.getUsername()+" "+user_details.getAuthorities();
	}
	
	@RequestMapping("/callerlogin")
	public String XYZService()
	{
		XYZ xyz=new XYZ();
		UserDetails user_details = (UserDetails)SecurityContextHolder
				.getContext()
				.getAuthentication().getPrincipal();
		return "Caller : Successfully logged in 1111!"+user_details.getUsername()+" "+user_details.getAuthorities()+xyz.anonymous();
		
	}

	@RequestMapping("/userlogin2")
	public String userValidation2() {
		return "User: Successfully logged in 2222!";
	}

	@RequestMapping("/adminlogin")
	public String adminValidation() {
		return "Admin: Successfully logged in!";
	}

	@RequestMapping("/slrlogin")
	public String slrValidation() {
		return "Seller: Successfully logged in!";
	}
}

class XYZ
{
	@Secured("CALLER")
	public String anonymous() {
	return "Hello World!!! from XyzService method ";
	}



	@Secured("XYZ_ADMIN")
	public String hasAdminRole() {
	return "Hello World!!! from XYZ";
	}
}