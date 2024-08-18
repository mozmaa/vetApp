package vetApp.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class MicroChip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String number;
	
	@Column(nullable = false)
	private LocalDate microchippingDate;
	
	@Column(nullable = false)
	private String microchipLocation;
	
	@OneToOne
	@JoinColumn(name = "ambulance_id" , referencedColumnName = "id")
	private Ambulance ambulance;
	
	@OneToOne
	@JoinColumn(name = "employee_id" , referencedColumnName = "id")
	private Employee employee;
	
	@OneToOne(mappedBy = "microChip")
	private Pet pet;

	public MicroChip() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public LocalDate getMicrochippingDate() {
		return microchippingDate;
	}

	public void setMicrochippingDate(LocalDate microchippingDate) {
		this.microchippingDate = microchippingDate;
	}

	public String getMicrochipLocation() {
		return microchipLocation;
	}

	public void setMicrochipLocation(String microchipLocation) {
		this.microchipLocation = microchipLocation;
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

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
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
		MicroChip other = (MicroChip) obj;
		return Objects.equals(id, other.id);
	}
	
}
