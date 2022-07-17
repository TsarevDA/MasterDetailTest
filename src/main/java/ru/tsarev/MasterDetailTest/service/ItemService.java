package ru.tsarev.MasterDetailTest.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.tsarev.MasterDetailTest.model.Item;
import ru.tsarev.MasterDetailTest.model.Position;
import ru.tsarev.MasterDetailTest.repository.ItemRepository;

@Service
@Transactional(readOnly = true)
public class ItemService {

	private final ItemRepository itemRepository;
	private final PositionService positionService;

	public ItemService(ItemRepository itemRepository, PositionService positionService) {
		this.itemRepository = itemRepository;
		this.positionService = positionService;
	}

	public Page<Item> getAll(Pageable pageable) {
		int page = pageable.getPageNumber();
		int itemPerPage = pageable.getPageSize();
		return itemRepository.findAll(PageRequest.of(page, itemPerPage));
	}

	public Item getById(int id) {
		Optional<Item> item = itemRepository.findById(id);
		return item.orElse(null);
	}

	@Transactional
	public void create(Item item) {
		itemRepository.save(item);
	}

	@Transactional
	public void update(int id, Item item) {
		item.setId(id);
		itemRepository.save(item);
	}

	@Transactional
	public void deleteById(int id) {
		Position position = positionService.getByItem(Item.builder().id(id).build());
		if (position != null)
			positionService.deleteById(position.getId());
		itemRepository.deleteById(id);
	}
}