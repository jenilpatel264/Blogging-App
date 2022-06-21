package com.blog.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class postResponse {
	
	private List<postDTO> content;
	private int pagesize;
	private int pagenumber;
	private int totalpage;
	private long totalelements;
	 private boolean lastpage;
	

}
