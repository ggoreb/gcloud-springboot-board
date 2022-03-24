package com.example.board;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BoardApplicationTests {

	@Test
	void contextLoads() {
	  PasswordEncoder pe = new BCryptPasswordEncoder();
	  // $2a$10$Ksr54SYu7BWw/vtwDuNTMeSXhRCGxSKovqNXCQzrwPuycOh.IVLRO
	  // $2a$10$1sX7PCDeUl2aMsnl6ri10.ECxDlxH3I0NGWlJjGy84E6ks4himJ7e
	  String pw = pe.encode("1234");
	  System.out.println(pw);
	  
	  boolean isMatch = 
	      pe.matches(
	          "1234", 
	          "$2a$10$Ksr54SYu7BWw/vtwDuNTMeSXhRCGxSKovqNXCQzrwPuycOh.IVLRO");
	  System.out.println(isMatch);
	}

}





