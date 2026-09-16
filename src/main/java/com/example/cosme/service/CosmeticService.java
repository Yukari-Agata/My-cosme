package com.example.cosme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cosme.Repository.CosmeticRepository;
import com.example.cosme.domain.Cosmetic;

@Service
@Transactional
public class CosmeticService {

    @Autowired
    private CosmeticRepository cosmeticRepository;

    // 所持中コスメを全件取得
    public List<Cosmetic> findAll() {
        return cosmeticRepository.findAll();
    }

    // 名前で曖昧検索
    public List<Cosmetic> findByName(String name) {
        return cosmeticRepository.findByName(name);
    }

    // ブランドで検索
    public List<Cosmetic> findByBrandId(Integer brandId) {
        return cosmeticRepository.findByBrandId(brandId);
    }

    // カテゴリで検索
    public List<Cosmetic> findByCategoryId(Integer categoryId) {
        return cosmeticRepository.findByCategoryId(categoryId);
    }

    // プチプラ / ハイブラで検索
    public List<Cosmetic> findByPriceType(Integer priceType) {
        return cosmeticRepository.findByPriceType(priceType);
    }

    // 新規登録
    public void insert(Cosmetic cosmetic) {
        cosmeticRepository.insert(cosmetic);
    }

    // 論理削除
    public void logicalDelete(Integer id, Integer repeatFlag) {
        cosmeticRepository.logicalDelete(id, repeatFlag);
    }

    // 削除済み一覧
    public List<Cosmetic> findDeleted() {
        return cosmeticRepository.findDeleted();
    }
}
