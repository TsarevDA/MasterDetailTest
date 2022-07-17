package ru.tsarev.MasterDetailTest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.tsarev.MasterDetailTest.model.Item;
import ru.tsarev.MasterDetailTest.model.Position;
import ru.tsarev.MasterDetailTest.repository.PositionRepository;

@Service
@Transactional(readOnly = true)
public class PositionService {

	private final PositionRepository positionRepository;

	public PositionService(PositionRepository positionRepository) {
		this.positionRepository = positionRepository;
	}

	public List<Position> getAll() {
		return positionRepository.findAll();
	}

	public Position getById(int id) {
		Optional<Position> position = positionRepository.findById(id);
		return position.orElse(null);
	}

	@Transactional
	public void create(Position position) {

		Position newPos = getByItem(position.getItem());
		if (newPos != null) {
			newPos.setAmount(newPos.getAmount() + position.getAmount());
			update(newPos.getId(), newPos);
		} else
			positionRepository.save(position);
	}

	@Transactional
	public void update(int id, Position position) {
		position.setId(id);
		positionRepository.save(position);
	}

	@Transactional
	public void deleteById(int id) {
		positionRepository.deleteById(id);
	}

	@Transactional
	public void deleteByItem(Item item) {
		Position position = positionRepository.findByItem(item);
		positionRepository.deleteById(position.getId());
	}

	public Position getByItem(Item item) {
		return positionRepository.findByItem(item);
	}

}