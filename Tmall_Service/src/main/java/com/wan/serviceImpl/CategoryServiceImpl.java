package com.wan.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wan.dao.CategoryDao;
import com.wan.pojo.Category;
import com.wan.pojo.Page4Navigator;
import com.wan.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @Author 万星明
 * @Date 2019/2/16
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryDao categoryDao;


    /**
     * 查询全部商品分类,并倒序排列
     * @return
     */
    @Override
    public List<Category> queryAllCateGory() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");//通过ID倒序排列
        return categoryDao.findAll(sort);
    }


    /**
     * 查询全部商品分类,并倒序排序且传递给分页包装类,进行分页
     * @param start
     * @param size
     * @param navigatePages
     * @return
     */
    @Override
    public Page4Navigator<Category> queryAllCateGory(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");//通过ID倒序排列

        //通过JPA自带的PageRequest分页请求,创建Pageable分页对象
        Pageable pageable = PageRequest.of(start, size, sort);
        //通过Dao层从JPA中获取分页好的商品分类集合
        Page pageFromJPA = categoryDao.findAll(pageable);

        System.out.println("分页包装类调用成功:"+pageFromJPA);

        //将分页的商品分类集合传给包装类,通过包装类设置显示页码数navigatePages
        Page4Navigator<Category> page4Navigator = new Page4Navigator<Category>(pageFromJPA, navigatePages);
        //返回分页包装类
        return page4Navigator;
    }
}
