package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.UserLogin;

/**
 * @author Piyush.Thakre
 *
 */
public interface IUserLoginDao extends JpaRepository<UserLogin, Long> {
	
	@Query("select userLogin from UserLogin userLogin where active=true")
	List<UserLogin> getAllActiveUserLogins();

	@Query("select userLogin from UserLogin userLogin where userLogin.userLoginId=?1 ")
	List<UserLogin> getUserLoginByUserLoginId(Long userLoginId);
	
	@Query("select userLogin from UserLogin userLogin where userLogin.userName=:userName and userLogin.token=:token")
	List<UserLogin> getUserLoginByuserNameAndToken(String userName,String token);
	
	@Query("select userLogin from UserLogin userLogin where userLogin.userName=:userName and userLogin.password=:password and userLogin.active=true")
	UserLogin getUserLoginByuserNameAndPassword(String userName,String password);
}

