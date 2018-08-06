package com.example.Human.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.Human.entity.Products;

@Mapper
public interface ProductsMapper {
	
	List<Products> selectAll();
	
}
