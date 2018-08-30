package com.example.Human.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.Human.entity.ProductsDetail;

@Mapper
public interface ProductsDetailMapper {

	 List<ProductsDetail> products_detail();
}
