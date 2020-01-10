package com.softeem.service;

import com.softeem.bean.Performer;
import com.softeem.dao.PerformerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformerService {
    @Autowired
    private PerformerRepository performerRepository;

    public boolean addPerformer(Performer performer){
      return   performerRepository.addAPerformer(performer);
    }
}
