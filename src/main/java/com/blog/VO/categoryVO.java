package com.blog.VO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class categoryVO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryId;
	@Column(name="category_title",length = 100,nullable = false)
	private String categoryTitle;
	private String categoryDescription;
	
	@OneToMany(mappedBy = "categoryVO",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<postVO> posts=new ArrayList<>();

}
