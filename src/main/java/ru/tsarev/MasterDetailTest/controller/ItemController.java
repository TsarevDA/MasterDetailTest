package ru.tsarev.MasterDetailTest.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.tsarev.MasterDetailTest.model.Item;
import ru.tsarev.MasterDetailTest.service.ItemService;

@Controller
@RequestMapping("/masterdetailtest")
public class ItemController {
	
	private final ItemService itemService;
	
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
		
	}

	@GetMapping("items/{id}")
	public String getById(@PathVariable("id") int id, Model model) {
		model.addAttribute("item", itemService.getById(id));
		return "show";
	}
	
	@GetMapping("/items/delete")
	public String deleteById(@RequestParam int id) {
		itemService.deleteById(id);
		return "redirect:/masterdetailtest/items";
		
	}
	
	@GetMapping("/items/new")
	public String returnNewItem(Model model) {
		model.addAttribute("item", Item.builder().build());
		return "item/new";
	}
	
	@GetMapping("/items")
	public String getAll(Model model,@PageableDefault(value = 10) Pageable pageable) {
		model.addAttribute("items", itemService.getAll(pageable));
		return "item/index";
	}

	@PostMapping("/items/create")
	public String createGroup(@ModelAttribute Item item) {
		itemService.create(item);
		return "redirect:/masterdetailtest/items";
	}

}