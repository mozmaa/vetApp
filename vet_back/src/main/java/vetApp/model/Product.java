package vetApp.model;

import javax.persistence.*;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(nullable = false)
	private String productCode;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private double preTaxedPrice;
	
	@Column(nullable = false)
	private double taxedPrice;
	
	@Column()
	private String productDescription;
	
	@Column(nullable = false)
	private boolean forSale;
	
	@Column
	private String unitOfMeasure;
	
	@Column
	private int quantity;
	
	public Product () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPreTaxedPrice() {
		return preTaxedPrice;
	}

	public void setPreTaxedPrice(double preTaxedPrice) {
		this.preTaxedPrice = preTaxedPrice;
	}

	public double getTaxedPrice() {
		return taxedPrice;
	}

	public void setTaxedPrice(double taxedPrice) {
		this.taxedPrice = (taxedPrice * this.quantity) * 0.18;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public boolean isForSale() {
		return forSale;
	}

	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
