package ru.tsarev.MasterDetailTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.tsarev.MasterDetailTest.model.Item;
import ru.tsarev.MasterDetailTest.model.Position;

@Repository
public interface PositionRepository extends JpaRepository <Position, Integer> {
	
	Position findByItem(Item item);
	
}