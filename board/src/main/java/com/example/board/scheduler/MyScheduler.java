package com.example.board.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MyScheduler {
  
  //                 초 분 시 일 월 요일
  @Scheduled(cron = "10 * * * * *")
  public void test() { 
    System.out.println("TEST!!!!");
  }
  
}
