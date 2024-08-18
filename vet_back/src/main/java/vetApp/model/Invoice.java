package vetApp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Invoice extends Treatment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(nullable = false)
	private LocalDate dateOfInvoce;
	
	@Column(nullable = false)
	private double totalPrice;

	public Invoice() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateOfInvoce() {
		return dateOfInvoce;
	}

	public void setDateOfInvoce(LocalDate dateOfInvoce) {
		this.dateOfInvoce = dateOfInvoce;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		int totalProductPrice = 0;
		List<Product> products = getProducts();
		if(!products.isEmpty()) {
			for(int i = 0; i < products.size(); i++) {
				totalProductPrice += products.get(i).getTaxedPrice();
			}
		}
		this.totalPrice = getProceduresPrice() + totalProductPrice ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		return Objects.equals(id, other.id);
	}

}
