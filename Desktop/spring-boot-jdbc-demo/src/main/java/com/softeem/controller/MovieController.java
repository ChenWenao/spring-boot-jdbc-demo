package com.softeem.controller;

import com.softeem.bean.Movie;
import com.softeem.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    // 在 URL 中【嵌】一个id。对于这种【嵌】在URL中的id，通过 @PathVariable 注解【抠】出来。
    @GetMapping("/movies/{id}")
    public Movie get(@PathVariable String id) {
        return movieService.getMovie(id);
    }

    @PutMapping("/put")
    public void put(String username, String password) {
        log.info("{} {}", username, password);
    }
}
