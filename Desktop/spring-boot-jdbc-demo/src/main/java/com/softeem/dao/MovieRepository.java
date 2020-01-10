package com.softeem.dao;

import com.softeem.bean.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 国外喜欢叫 Repository，取【仓库】的含义。
 * 国内喜欢叫 DAO（Data Access Object），数据访问对象。
 *
 * 项目中的 Repository/DAO 都应该是单例对象，
 * 而单例对象的创建、管理、维护工作，我们都是委托给 Spring 来负责。
 *
 * Spring boot 项目启动后，spring 发现该类的脑袋上面有 @Repository 注解，
 * 那么 Spring 就会自动创建这个类的单例对象，有需要的话，还会为该对象的属性赋值。
 */
@Repository
public class MovieRepository {

    @Autowired  // 让 Spring 在它的【容器】中，找到一个 JdbcTemplate 类型的单例对象，为本属性赋值。
    private JdbcTemplate template;

    public Movie selectMovieById(String id) {
        MovieRowMapper mapper = new MovieRowMapper();
        List<Movie> list = template.query("select movie.id,movie.name,\n" +
                        "    GROUP_CONCAT(distinct if(mid_movie_performer.role=1,performer.name,null)) as director,\n" +
                        "    GROUP_CONCAT(distinct if(mid_movie_performer.role=2,performer.name,null)) as writer,\n" +
                        "    GROUP_CONCAT(distinct if(mid_movie_performer.role=3,performer.name,null)) as actor,\n" +
                        "       movie.plot,\n" +
                        "    GROUP_CONCAT(distinct movie_type.name) as type\n" +
                        "from movie,mid_movie_type,mid_movie_performer,performer,movie_type\n" +
                        "where mid_movie_type.movie_type_id=movie_type.id\n" +
                        "and movie.id=mid_movie_type.movie_id\n" +
                        "and mid_movie_performer.performer_id=performer.id\n" +
                        "and movie.id=mid_movie_performer.movie_id\n" +
                        "and mid_movie_performer.performer_id=performer.id\n" +
                        "and mid_movie_type.movie_type_id=movie_type.id\n" +
                        "and movie.id=?\n" +
                        "group by movie.id;\n",// 代表一种映射规则：以列名和属性名为依据。
                 mapper ,                                          // 查询结果集中的 xxx 列的数据，为 Movie 对象的 xxx 属性赋值。
                id);

        return list.get(0);
    }

    public Movie selectByPK(String id) {

        MovieRowMapper mapper = new MovieRowMapper();
        List<Movie> list = template.query("select * from mid_movie_performer ,performer where movie_id = ?",
                mapper,id);
        return list.get(0);
    }

    public List<Movie> selectMovieByDirector(String name) {
        MovieRowMapper mapper=new MovieRowMapper();
        List<Movie> list = template.query(
                "select movie.id ,movie.name,\n" +
                        "        GROUP_CONCAT(distinct if(mid_movie_performer.role=1,performer.name,null)) as director,\n" +
                        "        GROUP_CONCAT(distinct if(mid_movie_performer.role=2,performer.name,null)) as writer,\n" +
                        "        GROUP_CONCAT(distinct if(mid_movie_performer.role=3,performer.name,null)) as actor,\n" +
                        "        movie.plot,\n" +
                        "        GROUP_CONCAT(distinct movie_type.name) as type\n" +
                        "from movie,performer,mid_movie_performer,movie_type,mid_movie_type\n" +
                        "where movie.id=(\n" +
                        "    select mid_movie_performer.movie_id\n" +
                        "    from mid_movie_performer\n" +
                        "    where performer_id=(\n" +
                        "        select id\n" +
                        "        from performer\n" +
                        "        where performer.name=?)\n" +
                        "    and role=1 )\n" +
                        "and movie.id=mid_movie_performer.movie_id\n" +
                        "and mid_movie_performer.performer_id=performer.id\n" +
                        "and movie.id=mid_movie_type.movie_id\n" +
                        "and mid_movie_type.movie_type_id=movie_type.id\n" +
                        "group by movie.id;\n",
        mapper,
        name);
        return list;
    }


    public List<Movie> selectMovieByWriter(String name) {
        MovieRowMapper mapper=new MovieRowMapper();
        List<Movie> list = template.query(
                "select movie.id ,movie.name,\n" +
                        "        GROUP_CONCAT(distinct if(mid_movie_performer.role=1,performer.name,null)) as director,\n" +
                        "        GROUP_CONCAT(distinct if(mid_movie_performer.role=2,performer.name,null)) as writer,\n" +
                        "        GROUP_CONCAT(distinct if(mid_movie_performer.role=3,performer.name,null)) as actor,\n" +
                        "        movie.plot,\n" +
                        "        GROUP_CONCAT(distinct movie_type.name) as type\n" +
                        "from movie,performer,mid_movie_performer,movie_type,mid_movie_type\n" +
                        "where movie.id=(\n" +
                        "    select mid_movie_performer.movie_id\n" +
                        "    from mid_movie_performer\n" +
                        "    where performer_id=(\n" +
                        "        select id\n" +
                        "        from performer\n" +
                        "        where performer.name=?)\n" +
                        "    and role=2 )\n" +
                        "and movie.id=mid_movie_performer.movie_id\n" +
                        "and mid_movie_performer.performer_id=performer.id\n" +
                        "and movie.id=mid_movie_type.movie_id\n" +
                        "and mid_movie_type.movie_type_id=movie_type.id\n" +
                        "group by movie.id;\n",
                mapper,
                name);
        return list;
    }

    public List<Movie> selectMovieByActor(String name) {
        MovieRowMapper mapper=new MovieRowMapper();
        List<Movie> list = template.query(
                "select movie.id ,movie.name,\n" +
                        "        GROUP_CONCAT(distinct if(mid_movie_performer.role=1,performer.name,null)) as director,\n" +
                        "        GROUP_CONCAT(distinct if(mid_movie_performer.role=2,performer.name,null)) as writer,\n" +
                        "        GROUP_CONCAT(distinct if(mid_movie_performer.role=3,performer.name,null)) as actor,\n" +
                        "        movie.plot,\n" +
                        "        GROUP_CONCAT(distinct movie_type.name) as type\n" +
                        "from movie,performer,mid_movie_performer,movie_type,mid_movie_type\n" +
                        "where movie.id=(\n" +
                        "    select mid_movie_performer.movie_id\n" +
                        "    from mid_movie_performer\n" +
                        "    where performer_id=(\n" +
                        "        select id\n" +
                        "        from performer\n" +
                        "        where performer.name=?)\n" +
                        "    and role=3 )\n" +
                        "and movie.id=mid_movie_performer.movie_id\n" +
                        "and mid_movie_performer.performer_id=performer.id\n" +
                        "and movie.id=mid_movie_type.movie_id\n" +
                        "and mid_movie_type.movie_type_id=movie_type.id\n" +
                        "group by movie.id;\n",
                mapper,
                name);
        return list;
    }
    public List<Movie> selectMovieByType(String type) {
        MovieRowMapper mapper = new MovieRowMapper();
        List<Movie> list = template.query(
                "select movie.id ,movie.name,\n" +
                        "        GROUP_CONCAT(distinct if(mid_movie_performer.role=1,performer.name,null)) as director,\n" +
                        "        GROUP_CONCAT(distinct if(mid_movie_performer.role=2,performer.name,null)) as writer,\n" +
                        "        GROUP_CONCAT(distinct if(mid_movie_performer.role=3,performer.name,null)) as actor,\n" +
                        "        movie.plot,\n" +
                        "        GROUP_CONCAT(distinct movie_type.name) as type\n" +
                        "from movie,performer,mid_movie_performer,movie_type,mid_movie_type\n" +
                        "where movie.id in (select mid_movie_type.movie_id\n" +
                        "    from movie_type ,mid_movie_type\n" +
                        "    where mid_movie_type.movie_type_id=movie_type.id\n" +
                        "        and movie_type.name like ?)\n" +
                        "and movie.id=mid_movie_performer.movie_id\n" +
                        "and mid_movie_performer.performer_id=performer.id\n" +
                        "and movie.id=mid_movie_type.movie_id\n" +
                        "and mid_movie_type.movie_type_id=movie_type.id\n" +
                        "group by movie.id;",
                mapper,
                type);
        return list;
    }

    public boolean deleteMovieByIds(String[] ids){
        try {
            for(String id:ids) {
                template.update("SET foreign_key_checks = 0;");
                template.update("delete from movie where movie.id=?;" , id);
                template.update("delete from mid_movie_performer where mid_movie_performer.movie_id=?;" , id);
                template.update("delete from mid_movie_type where mid_movie_type.movie_id=?;" , id);
                template.update("SET foreign_key_checks = 1;");
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }



}



