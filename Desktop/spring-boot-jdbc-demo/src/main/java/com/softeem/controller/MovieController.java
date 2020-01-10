package com.softeem.controller;

import com.softeem.bean.Movie;
import com.softeem.bean.Performer;
import com.softeem.service.MovieService;
import com.softeem.service.PerformerService;
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

    @Autowired
    private PerformerService performerService;

    // 在 URL 中【嵌】一个id。对于这种【嵌】在URL中的id，通过 @PathVariable 注解【抠】出来。
    @GetMapping("/movies/directores/{director}")
    public List<Movie> getByDirectorName(@PathVariable("director") String director) {
        return movieService.getByDirectorName(director);
    }

    @GetMapping("/movies/writers/{writer}")
    public List<Movie> getByWriterName(@PathVariable("writer") String writer) {
        return movieService.getByWriterName(writer);
    }

    @GetMapping("/movies/actors/{actor}")
    public List<Movie> getByActorName(@PathVariable("actor") String actor) {
        return movieService.getByActorName(actor);
    }

    @GetMapping("/movies/types/{type}")
    public List<Movie> getByType(@PathVariable("type") String type) {
        return movieService.getByType(type);
    }

    @DeleteMapping("/movies/{id}")
    public boolean deleteByIds(@PathVariable("id") String[] ids) {
        return movieService.deleteByIds(ids);
    }

    @GetMapping("/movies/{id}/{name}")
    public boolean addPerformers(@PathVariable("id")String[] ids, @PathVariable("name")String[] names){

        for (int i = 0; i < ids.length; i++) {
            Performer performer = new Performer();
            performer.setId(ids[i]);
            performer.setName(names[i]);
            System.out.println(performer.getId());
            System.out.println(performer.getName());
           if (!performerService.addPerformer(performer))
               return false;
        }

        return true;
    }

    @PutMapping("/movies")
    public String updateById(Movie movie) {
        if (!movieService.updateById(movie))
            return "你插入的performer在数据库中不存在，请插入你要填入的Performer(id, name)!";
        return "恭喜你！更新数据成功!";
    }

    @PostMapping("/movies")
    public String postMovie(Movie movie){
        if (!movieService.addMovie(movie))
            return "你插入的performer在数据库中不存在，请插入你要填入的Performer(id, name)!";
        return "恭喜你！更新数据成功!";
    }

}
