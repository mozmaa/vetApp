package vetApp.model;

import java.util.Objects;

import javax.persistence.*;

@Entity
public class PetPassport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column
	private String serialNumber;
	
	@OneToOne(mappedBy = "passport")
	private Pet pet;
	
	@ManyToOne
	private User user;
	
	@OneToOne
	@JoinColumn(name = "ambulance_id" , referencedColumnName = "id")
	private Ambulance ambulance;
	
	@OneToOne
	@JoinColumn(name = "employee_id" , referencedColumnName = "id")
	private Employee employee;
	
	public PetPassport () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Ambulance getAmbulance() {
		return ambulance;
	}

	public void setAmbulance(Ambulance ambulance) {
		this.ambulance = ambulance;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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
		PetPassport other = (PetPassport) obj;
		return Objects.equals(id, other.id);
	}

}
