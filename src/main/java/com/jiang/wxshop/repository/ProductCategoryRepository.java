package com.jiang.wxshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jiang.wxshop.dataobject.ProductCategory;

/**
 * DAO层
 * @author jiangqianghua
 *
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList);
}
