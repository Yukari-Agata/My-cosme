package com.example.cosme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cosme.Repository.CategoryRepository;
import com.example.cosme.domain.Category;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
