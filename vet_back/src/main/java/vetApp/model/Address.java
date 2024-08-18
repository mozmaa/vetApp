package vetApp.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;


@Entity
public class Address {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Column( nullable =  false)
	 private String streetName;
	 
	 @Column( nullable = false)
	 private String buildingNumber;
	 
	 @Column()
	 private String appartmentNumber;
	 
	 @Column( nullable =  false)
	 private String city;
	 
	 @Column( nullable =  false)
	 private String zipCode;

	public Address() {

	}

	public Address(Long id, String streetName, String buildingNumber, String appartmentNumber, String city, String zipCode,
			List<User> users) {
		super();
		this.id = id;
		this.streetName = streetName;
		this.buildingNumber = buildingNumber;
		this.appartmentNumber = appartmentNumber;
		this.city = city;
		this.zipCode = zipCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getAppartmentNumber() {
		return appartmentNumber;
	}

	public void setAppartmentNumber(String appartmentNumber) {
		this.appartmentNumber = appartmentNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	//equals method where we handle nulls automatically
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(id, other.id);
	}
	 
	
}
