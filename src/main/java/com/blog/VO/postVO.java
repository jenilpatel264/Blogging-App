package com.blog.VO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post_table")
@NoArgsConstructor
@Setter
@Getter
public class postVO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	
	@ManyToOne
	private categoryVO categoryVO;
	
	@ManyToOne
	private userVO userVO;
	
	@OneToMany(mappedBy ="postVO",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<commentVO> comments=new HashSet<>();

}
