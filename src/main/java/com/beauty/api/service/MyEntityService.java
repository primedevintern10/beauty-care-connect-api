package com.beauty.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beauty.api.entity.MyEntity;
import com.beauty.api.repo.MyEntityRepository;

@Service
public class MyEntityService {
	private final MyEntityRepository myEntityRepository;

    @Autowired
    public MyEntityService(MyEntityRepository myEntityRepository) {
        this.myEntityRepository = myEntityRepository;
    }

    public MyEntity saveEntity(MyEntity entity) {
        return myEntityRepository.save(entity);
    }
}
