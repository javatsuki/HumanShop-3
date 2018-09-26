package com.example.Human.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.Human.entity.Products;

@Mapper
public interface ProductsMapper {
	
	List<Products> selectAll();
	
	List<Products> products_detail(String productsId);//Controllerの方と型を合わせる！
	
}
