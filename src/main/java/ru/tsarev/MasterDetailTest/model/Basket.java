package ru.tsarev.MasterDetailTest.model;

import java.util.List;

public class Basket {

	private int finalPrice;
	private List<Position> positions;

	public Basket() {

	}

	public int getFinalPrice() {
		calculateFinalPrice();
		return finalPrice;
	}

	public void setFinalPrice(int finalPrice) {
		this.finalPrice = finalPrice;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public void calculateFinalPrice() {
		int finalPrice = 0;
		for (Position position : positions) {
			finalPrice += position.calculatePositionPrice();
		}
		this.finalPrice = finalPrice;
	}

	@Override
	public String toString() {
		return "Basket [finalPrice=" + finalPrice + ", positions=" + positions + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + finalPrice;
		result = prime * result + ((positions == null) ? 0 : positions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Basket other = (Basket) obj;
		if (finalPrice != other.finalPrice)
			return false;
		if (positions == null) {
			if (other.positions != null)
				return false;
		} else if (!positions.equals(other.positions))
			return false;
		return true;
	}

	public static BasketBuilder builder() {
		return new BasketBuilder();
	}

	public static class BasketBuilder {

		private List<Position> positions;
		private int finalPrice;

		public BasketBuilder positions(List<Position> positions) {
			this.positions = positions;
			return this;
		}

		public BasketBuilder finalPrice(int finalPrice) {
			this.finalPrice = finalPrice;
			return this;
		}

		public Basket build() {
			Basket basket = new Basket();
			basket.setPositions(positions);
			basket.setFinalPrice(finalPrice);
			return basket;
		}
	}
}
