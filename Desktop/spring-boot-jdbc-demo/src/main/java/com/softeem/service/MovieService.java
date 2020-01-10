
package com.softeem.service;

import com.softeem.bean.Movie;
import com.softeem.dao.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 为什么 MVC 分层有三层的概念：controller、service、dao 层？
 * 为什么不是 controller 层调用 dao 层的方法，要去调 service，再通过service 调 dao？
 *
 * 以【转账】为例：
 * Service 层中的每一个方法，对应的是人类世界中/人脑子里/人的概念中的【干一件事情】。
 * dao 层中的每一个方法，有且仅执行一条 SQL 语句。
 * 关键在于：人【认为】的【干一件事情】的背后，可能会执行多条 SQL 语句。
 * 【转账】这是人类世界中的一件事情，但是其背后，至少有两条（update）SQL语句 。
 *
 * 所以，需要使用 Service 中的一个方法，将 DAO 中的多个方法【包裹】起来，作为一个整体。
 * 再通过【事务】的概念来确保这【几条SQL】要么都对数据库造成影响，要么没有任何一条对数据库造成影响。
 * 因为，这毕竟是【一件事情】。
 *
 * 当然，有些【简单的干一件事情】背后有且仅执行一条 SQL 语句，
 * Service 中的方法调用的 Dao 的方法的数量就是 1：1 的关系。
 */
@Service    // 和 Repository/DAO 的情况一样，让 Spring 发现并创建 Service 的单例对象。
public class MovieService {

    @Autowired  // Spring 从其【容器】中找一个 MovieRepository 的单例对象，来为这个属性赋值。
    private MovieRepository movieRepository;

    public Movie getById(String id){ return movieRepository.selectMovieById(id);}

    public List<Movie> getByDirectorName(String name) {
        return movieRepository.selectMovieByDirector(name);
    }

    public List<Movie>  getByWriterName(String name){
        return movieRepository.selectMovieByWriter(name);
    }

    public List<Movie> getByActorName(String name) {
        return movieRepository.selectMovieByActor(name);
    }

    public List<Movie> getByType(String type){
        return movieRepository.selectMovieByType(type);
    }

    public boolean deleteByIds(String[] ids) {
        return movieRepository.deleteMovieByIds(ids);
    }
//
//    public void updateByid(String id) {
//
//    }
//
//    public void addMovie(Movie movie){
//
//    }


}
