package com.beauty.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beauty.api.dto.MyEntityDTO;
import com.beauty.api.service.MyEntityService;

@RestController
@RequestMapping(value = "api/v1/category")
@CrossOrigin
public class MyController {
	@Autowired
    private MyEntityService myEntityService;


    @GetMapping("/getCategorys")

    public List<MyEntityDTO> getCategory(){

        return myEntityService.getAllCategory();
    }

    @PostMapping("/saveCategory")
    public MyEntityDTO saveCategory(@RequestBody MyEntityDTO myEntityDTO){

        return myEntityService.saveCategory(myEntityDTO);

    }

//    @PutMapping("/updateCategory/{id}")

//    public MyEntityDTO updateCategory(@PathVariable int id,@RequestBody MyEntityDTO myEntityDTO){
//    	myEntityDTO.(id);
//    	return myEntityService.updateCategory(myEntityDTO);
//    }

//    @DeleteMapping("/deleteCategory/{id}")

//    public boolean deleteCategory(@PathVariable int id){
//        return myEntityService.deleteCategory(id);  
//    }
}
