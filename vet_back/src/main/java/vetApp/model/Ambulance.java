package vetApp.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Ambulance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String phoneNumber;
	
	@Column
	private String mobilePhoneNumber;
	
	@Column
	private boolean closed;
	
	@OneToOne
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "ambulance_id" , referencedColumnName = "id")
	private Set<Employee> employees = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "ambulance_id" , referencedColumnName = "id")
	private Set<Product> products = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "ambulance_id" , referencedColumnName = "id")
	private Set<Pet> pets = new HashSet<>();
		
	public Ambulance () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public Address getAddress() {
		return address;
	}
	
	
	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(Address adress) {
		this.address = adress;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<Pet> getPets() {
		return pets;
	}

	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ambulance other = (Ambulance) obj;
		return Objects.equals(id, other.id);
	}
	
}
