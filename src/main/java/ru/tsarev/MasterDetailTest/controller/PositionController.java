package ru.tsarev.MasterDetailTest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.tsarev.MasterDetailTest.model.Basket;
import ru.tsarev.MasterDetailTest.model.Position;
import ru.tsarev.MasterDetailTest.service.ItemService;
import ru.tsarev.MasterDetailTest.service.PositionService;

@Controller
@RequestMapping("/masterdetailtest")
public class PositionController {

	private final PositionService positionService;
	private final ItemService itemService;

	public PositionController(PositionService positionService, ItemService itemService) {
		this.positionService = positionService;
		this.itemService = itemService;
	}

	@GetMapping("/{id}")
	public String getById(@PathVariable("id") int id, Model model) {
		model.addAttribute("position", positionService.getById(id));
		return "show";
	}

	@GetMapping("/positions/add")
	public String addPosition(@RequestParam int itemId, Model model) {
		Position position = Position.builder().item(itemService.getById(itemId)).amount(1).build();
		positionService.create(position);
		return "redirect:/masterdetailtest/positions";
	}

	@GetMapping("/positions")
	public String getAll(Model model) {
		List<Position> positions = positionService.getAll();
		Basket basket = Basket.builder().positions(positions).build();
		model.addAttribute("finalPrice", basket.getFinalPrice());
		model.addAttribute("positions", positionService.getAll());
		return "position/index";
	}

	@GetMapping("/positions/delete")
	public String deleteById(@RequestParam int id) {
		positionService.deleteById(id);
		return "redirect:/masterdetailtest/positions";
	}
}
