package com.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class categoryDTO {
	private int categoryId;
	
	@NotBlank
	@Size(min = 4,message = "minimum charcter must be 4")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=5,message = "minimum charcter must be 5")
	private String categoryDescription;
}
