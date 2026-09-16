package com.example.cosme.Controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cosme.domain.Brand;
import com.example.cosme.domain.Cosmetic;
import com.example.cosme.service.BrandService;
import com.example.cosme.service.CategoryService;
import com.example.cosme.service.CosmeticService;
import com.example.cosme.form.CosmeticForm;

@Controller
@RequestMapping("/cosmetics")
public class CosmeticController {

        @Autowired
        private CosmeticService cosmeticService;
        @Autowired
        private CategoryService categoryService;
        @Autowired
        private BrandService brandService;

        // 一覧表示
        @GetMapping("")
        public String index(Model model) {

                List<Cosmetic> cosmeticList = cosmeticService.findAll();
                model.addAttribute("cosmeticList",cosmeticList);
                model.addAttribute("brandList",brandService.findAll());
                model.addAttribute("categoryList",categoryService.findAll());

                return "cosmetics";
        }

        // =========================
        // 名前で検索
        // =========================
        @GetMapping("/search/name")
        public String searchByName(@RequestParam("name") String name,Model model) {

                List<Cosmetic> cosmeticList = cosmeticService.findByName(name);
                model.addAttribute("cosmeticList",cosmeticList);

                return "cosmetics";
        }

        // ブランドで検索
        @GetMapping("/search/brand")
        public String searchByBrand(@RequestParam("brandId") Integer brandId,Model model) {

                model.addAttribute("cosmeticList",cosmeticService.findByBrandId(brandId));
                model.addAttribute("brandList",brandService.findAll());
                model.addAttribute("categoryList",categoryService.findAll());

                return "cosmetics";
        }

        // カテゴリで検索
        @GetMapping("/search/category")
        public String searchByCategory(@RequestParam("categoryId") Integer categoryId,Model model) {

                model.addAttribute("cosmeticList",cosmeticService.findByCategoryId(categoryId));
                model.addAttribute("brandList",brandService.findAll());
                model.addAttribute("categoryList",categoryService.findAll());

                return "cosmetics";
        }

        // =========================
        // プチプラ / ハイブランドで検索
        // =========================
        @GetMapping("/search/price")
        public String searchByPriceType(@RequestParam("priceType") Integer priceType,Model model) {

                List<Cosmetic> cosmeticList = cosmeticService.findByPriceType(priceType);
                model.addAttribute("cosmeticList",cosmeticList);

                return "cosmetics";
        }

        // =========================
        // 新規登録
        // =========================
        @PostMapping("/insert")
        public String insert(CosmeticForm form) {

                Cosmetic cosmetic = new Cosmetic();
                BeanUtils.copyProperties(form,cosmetic);
                cosmeticService.insert(cosmetic);

                return "redirect:/cosmetics";
        }

        // 論理削除
        @PostMapping("/delete")
        public String delete(@RequestParam("id") Integer id,@RequestParam("repeatFlag") Integer repeatFlag) {
                cosmeticService.logicalDelete(id,repeatFlag);

                return "redirect:/cosmetics";
        }

        // 使い終わり一覧
        @GetMapping("/deleted")
        public String deletedList(Model model) {

                List<Cosmetic> cosmeticList = cosmeticService.findDeleted();
                model.addAttribute("cosmeticList",cosmeticList);

                return "cosmetics-deleted";
        }

        // 使い終わりページ
        @GetMapping("/finish/{id}")
        public String finish(@PathVariable("id") Integer id,Model model) {
                model.addAttribute("cosmeticId", id);

                return "cosmetic-finish";
        }

        // 新規登録画面
        @GetMapping("/insert")
        public String insertPage(Model model) {

                model.addAttribute("brandList",brandService.findAll());
                model.addAttribute("categoryList",categoryService.findAll());

                return "cosmetic-insert";
        }
}
