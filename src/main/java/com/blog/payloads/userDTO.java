package com.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.blog.VO.roleVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class userDTO {
	
	private int id;
	@NotEmpty
	@Size(min = 4,message = "username must be greter then charcters")
	private String name;
	
	@Email(message = "your email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=3,max = 10,message = "password must be between 3 to 10 charcters")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<roleDTO> roleVOs=new HashSet<>();

}
