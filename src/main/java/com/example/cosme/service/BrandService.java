package com.example.cosme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cosme.Repository.BrandRepository;
import com.example.cosme.domain.Brand;

@Service
@Transactional
public class BrandService {

    @Autowired 
    private BrandRepository brandRepository;

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

}
