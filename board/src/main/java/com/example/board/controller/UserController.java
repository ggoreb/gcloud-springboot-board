package com.example.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.board.model.User;
import com.example.board.repository.UserRepository;

@Controller
public class UserController {
  @Autowired
  UserRepository userRepository;

  @Autowired
  HttpSession session;

  // 중복 로그인 방지!! session listener
  @GetMapping("/signout")
  public String signout() {
    session.removeAttribute("user_info"); // user_info 만 삭제
    session.invalidate(); // 세션의 모든 값 삭제
    return "redirect:/";
  }

  @GetMapping("/signin")
  public String signin() {
    return "signin";
  }

  @PostMapping("/signin")
  public String signinPost(@ModelAttribute User user) {
    PasswordEncoder pe = new BCryptPasswordEncoder();
    
//    User dbUser = userRepository.findByEmailAndPwd(user.getEmail(), user.getPwd());
//    if (dbUser != null) {
//      session.setAttribute("user_info", dbUser);
//    }
    
    User dbUser = userRepository.findByEmail(user.getEmail());
    if(dbUser != null) {
      //      사용자가 입력한 비번(원본)    DB에 저장된 암호화된 비번(변경)
      if(pe.matches(user.getPwd(), dbUser.getPwd())) {
        session.setAttribute("user_info", dbUser);
      }
    }
    
    return "redirect:/";
  }

  @GetMapping("/signup")
  public String signup() {
    return "signup";
  }

  @PostMapping("/signup")
  public String signupPost(@ModelAttribute User user) {
    PasswordEncoder pe = new BCryptPasswordEncoder();
    String pw = pe.encode(user.getPwd());
    System.out.println(pw);
    
    user.setPwd(pw);
    
    userRepository.save(user);
    return "redirect:/";
  }
}








