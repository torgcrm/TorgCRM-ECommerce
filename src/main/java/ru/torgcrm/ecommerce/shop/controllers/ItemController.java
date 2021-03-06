package ru.torgcrm.ecommerce.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.torgcrm.ecommerce.shop.models.Item;
import ru.torgcrm.ecommerce.shop.models.Response;
import ru.torgcrm.ecommerce.shop.repository.ItemRepository;
import ru.torgcrm.ecommerce.shop.repository.ResponseRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController extends BasicShopController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ResponseRepository responseRepository;

    @RequestMapping("/{slug}")
    public String itemDetails(@PathVariable(name = "slug") String slug,
                              Model model) {
        List<Response> responses = responseRepository.findAll();
        Item item = (Item) itemRepository.findBySlug(slug);
        model.addAttribute("item", item);
        model.addAttribute("responses", responses);
        feelModel(model);
        return getViewTemplate("item/details");
    }

    @Override
    public String index(Model modelAndView, HttpServletRequest request) {
        throw new NotImplementedException();
    }
}
