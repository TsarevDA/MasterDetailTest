package ru.tsarev.MasterDetailTest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Position")
public class Position {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "item_id", referencedColumnName = "id")
	private Item item;

	@Column(name = "amount")
	private int amount;

	private Position() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int calculatePositionPrice() {
		return item.getPrice() * amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		Position other = (Position) obj;
		if (amount != other.amount)
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PositionItem [item=" + item + ", amount=" + amount + "]";
	}

	public static PositionBuilder builder() {
		return new PositionBuilder();
	}

	public static class PositionBuilder {

		private int id;
		private Item item;
		private int amount;

		public PositionBuilder id(int id) {
			this.id = id;
			return this;
		}

		public PositionBuilder item(Item item) {
			this.item = item;
			return this;
		}

		public PositionBuilder amount(int amount) {
			this.amount = amount;
			return this;
		}

		public Position build() {
			Position position = new Position();
			position.setId(id);
			position.setItem(item);
			position.setAmount(amount);
			return position;
		}
	}
}
