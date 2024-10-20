package com.catbd.cat.controller.service.hibernate;

import com.catbd.cat.entity.HibernateCat;
import com.catbd.cat.repositories.HibernateCatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateHibernateCatService {

    @Autowired
    private HibernateCatRepository hibernateCatRepository;

    public HibernateCat createHibernateCat(HibernateCat cat) {
        return hibernateCatRepository.save(cat);
    }

}
