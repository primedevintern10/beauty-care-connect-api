package com.beauty.api.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beauty.api.dto.MyEntityDTO;
import com.beauty.api.entity.MyEntity;
import com.beauty.api.repo.MyEntityRepository;

@Service
@Transactional
public class MyEntityService {
	private final MyEntityRepository myEntityRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public MyEntityService(MyEntityRepository myEntityRepository) {
        this.myEntityRepository = myEntityRepository;
    }

    public MyEntityDTO saveCategory(MyEntityDTO MyEntityDTO){
    	MyEntity category = myEntityRepository.save(modelMapper.map(MyEntityDTO, MyEntity.class));
        return modelMapper.map(category, MyEntityDTO.class);
    }

    public List<MyEntityDTO>getAllCategory(){
        List<MyEntity>categoryList = myEntityRepository.findAll();
        return modelMapper.map(categoryList,new TypeToken<List<MyEntityDTO>>(){}.getType());
    }

    public MyEntityDTO updateCategory(MyEntityDTO MyEntityDTO){
    	myEntityRepository.save(modelMapper.map(MyEntityDTO,MyEntity.class));
        return MyEntityDTO;
    }

//    public boolean deleteCategory(int id){
//    	myEntityRepository.deleteById(id);
//        return true;
//    }
}
