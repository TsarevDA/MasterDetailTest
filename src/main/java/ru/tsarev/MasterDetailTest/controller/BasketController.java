package ru.tsarev.MasterDetailTest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import ru.tsarev.MasterDetailTest.model.Basket;
import ru.tsarev.MasterDetailTest.model.Position;
import ru.tsarev.MasterDetailTest.service.PositionService;

@Controller
@RequestMapping("/masterdetailtest")
public class BasketController {

	private final PositionService positionService;
	
	public BasketController(PositionService positionService) {
		this.positionService = positionService;
	}
	
	@GetMapping("")
	public String getBasket(Model model) {
		return  "basket/index";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		List<Position> positions = positionService.getAll();
		if (positions.size()==0) return "basket/checkoutEmptyBasket";
		Basket basket = Basket.builder().positions(positions).build();
		model.addAttribute("finalPrice", basket.getFinalPrice());
		model.addAttribute("positions", positionService.getAll());
		for(Position position:positions) positionService.deleteById(position.getId());
		return  "basket/checkout";
	}
}