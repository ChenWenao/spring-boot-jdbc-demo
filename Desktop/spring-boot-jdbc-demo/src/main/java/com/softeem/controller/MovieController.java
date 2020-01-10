package com.softeem.controller;

import com.softeem.bean.Movie;
import com.softeem.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    // 在 URL 中【嵌】一个id。对于这种【嵌】在URL中的id，通过 @PathVariable 注解【抠】出来。
    @GetMapping("/movies/directores/{director}")
    public List<Movie> getByDirectorName(@PathVariable("director") String director) {
       return  movieService.getByDirectorName(director);
    }

    @GetMapping("/movies/writers/{writer}")
    public List<Movie> getByWriterName(@PathVariable("writer") String writer) {
        return  movieService.getByWriterName(writer);
    }

    @GetMapping("/movies/actors/{actor}")
    public List<Movie> getByActorName(@PathVariable("actor") String actor) {
        return  movieService.getByActorName(actor);
    }

    @GetMapping("/movies/types/{type}")
    public List<Movie> getByType(@PathVariable("type") String type) {
        return  movieService.getByType(type);
    }

    @DeleteMapping("/movies/{id}")
    public boolean deleteByids(@PathVariable("id")String[] ids) {
        return movieService.deleteByIds(ids);
    }


    @PutMapping("/put")
    public void put(String username, String password) {
        log.info("{} {}", username, password);
    }
}
