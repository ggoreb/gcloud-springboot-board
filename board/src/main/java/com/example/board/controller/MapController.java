package com.example.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.model.Point;
import com.example.board.repository.PointRepository;

@Controller
public class MapController {

  @Autowired
  PointRepository pointRepository;

  @GetMapping("/map/getPoint")
  @ResponseBody
  public List<Point> getPoint(
      double lat, double lng, int km) {
//    List<Point> list = pointRepository.findAll();
    List<Point> list = 
        pointRepository.findByLatLng(
            lat, lng, lat, km);
    return list;
  }

  @GetMapping("/map")
  public String map() {
    return "map/map";
  }
}