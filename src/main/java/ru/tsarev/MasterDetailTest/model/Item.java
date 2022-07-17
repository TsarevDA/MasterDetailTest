package ru.tsarev.MasterDetailTest.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

@Entity
@Table(name = "Item")
public class Item {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private int price;

	@OneToMany(mappedBy = "item")
	private List<Position> positions;

	public Item() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + price;
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
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price != other.price)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", price=" + price + "]";
	}

	public static ItemBuilder builder() {
		return new ItemBuilder();
	}

	public static class ItemBuilder {

		private int id;
		private String name;
		private int price;

		public ItemBuilder() {

		}

		public ItemBuilder id(int id) {
			this.id = id;
			return this;
		}

		public ItemBuilder name(String name) {
			this.name = name;
			return this;
		}

		public ItemBuilder price(int price) {
			this.price = price;
			return this;
		}

		public Item build() {
			Item item = new Item();
			item.setId(id);
			item.setName(name);
			item.setPrice(price);
			return item;
		}
	}
}
