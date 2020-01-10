package com.softeem.dao;

import com.softeem.bean.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 未来，spring-jdbc 会循环/反复调用 MovieRowMapper，将结果集中的每一条数据，传入下述方法。
 * 逻辑上，mapRow()方法需要去干的事情，就是从结果集中拿出数据，封装到一个 Movie 对象中，并返回。
 *
 */
public class MovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getString("id"));
        movie.setName(rs.getString("name"));
        movie.setDirector(rs.getString("director"));
        movie.setWriter(rs.getString("writer"));
        movie.setActor(rs.getString("actor"));
        movie.setPlot(rs.getString("plot"));
        movie.setType(rs.getString("type"));
        return movie;
    }

}
