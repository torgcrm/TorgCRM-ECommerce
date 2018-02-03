package ru.torgcrm.ecommerce.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.torgcrm.ecommerce.shop.interceptors.BasicShopInterceptor;
import ru.torgcrm.ecommerce.shop.models.Category;
import ru.torgcrm.ecommerce.shop.models.Item;
import ru.torgcrm.ecommerce.shop.repository.CategoryRepository;
import ru.torgcrm.ecommerce.shop.repository.ItemRepository;

import java.util.List;

@Controller
public class CategoryController extends BasicShopController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ItemRepository itemRepository;

    @RequestMapping("/category/{slug}")
    public String categoryIndex(@PathVariable(value = "slug") String slug,
                                @RequestAttribute(name = BasicShopInterceptor._categoriesRequestAttribute) List categories,
                                Model model) {
        Category currentCategory = categoryRepository.findBySlug(slug);
        List<Item> items = itemRepository.findByCategoryId(currentCategory.getId());
        model.addAttribute("categories", categories);
        model.addAttribute("items", items);
        return "category/index";
    }
}
