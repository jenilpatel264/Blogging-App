package com.blog.payloads;

import lombok.Data;

@Data
public class JwtAuthRequests {
	
	private String username;
	private String password;

}
