package com.softeem.dao;


import com.softeem.bean.Performer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PerformerRepository {

    @Autowired
    private JdbcTemplate template;

    public void addAPerformer(Performer performer){
        template.update("insert into performer (id, name)\n" +
                "values (?,?);",performer.getId(),performer.getName());
    }
}
