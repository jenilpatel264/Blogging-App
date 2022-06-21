package com.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ManyToOne;

import com.blog.VO.categoryVO;
import com.blog.VO.commentVO;
import com.blog.VO.userVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class postDTO {
	private int postId;
	public String title;
	public String content;
	private String imageName;
	private Date addedDate;

	private categoryDTO categoryVO;

	private userDTO userVO;
	private Set<commentDTO> comments=new HashSet<>();

}
