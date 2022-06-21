package com.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.Config.appConstants;
import com.blog.DAO.roleDAO;
import com.blog.VO.roleVO;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner{
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired roleDAO dao;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.encoder.encode("jenil"));
		
		try {
			roleVO roleVO=new roleVO();
			roleVO.setId(appConstants.admin_user);
			roleVO.setName("ROLE_ADMIN");
			
			roleVO roleVO1=new roleVO();
			roleVO1.setId(appConstants.normal_user);
			roleVO1.setName("ROLE_NORMAL");
			
			List<roleVO> list=List.of(roleVO,roleVO1);
			List<roleVO> list2=this.dao.saveAll(list);
			
			list2.forEach(r->{
				System.out.println(r.getName());
			});
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	

}
