package vetApp.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Treatment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(nullable = false)
	private LocalDate dateOfTreatment;
	
	@Column(nullable = false)
	private String procedures;
	
	@Column
	private double proceduresPrice;
	
	@OneToOne
	@JoinColumn(name = "ambulance_id" , referencedColumnName = "id")
	private Ambulance ambulance;
	
	@OneToOne
	@JoinColumn(name = "employee_id" , referencedColumnName = "id")
	private Employee employee;
	
	@OneToMany
    @JoinTable(
        name = "treatment_product",
        joinColumns = @JoinColumn(name = "treatment_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
	private List<Product> products = new LinkedList<Product>(); //make join table
	
	@OneToMany
    @JoinTable(
        name = "treatment_diagnosis",
        joinColumns = @JoinColumn(name = "treatment_id"),
        inverseJoinColumns = @JoinColumn(name = "diagnosis_id")
    )
	private List<Diagnosis> diagnosis = new LinkedList<Diagnosis>(); //make join table
	
	public Treatment () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateOfTreatment() {
		return dateOfTreatment;
	}

	public void setDateOfTreatment(LocalDate dateOfTreatment) {
		this.dateOfTreatment = dateOfTreatment;
	}

	public String getProcedures() {
		return procedures;
	}

	public void setProcedures(String procedures) {
		this.procedures = procedures;
	}

	public double getProceduresPrice() {
		return proceduresPrice;
	}

	public void setProceduresPrice(double proceduresPrice) {
		this.proceduresPrice = proceduresPrice;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Diagnosis> getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(List<Diagnosis> diagnosis) {
		this.diagnosis = diagnosis;
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
		Treatment other = (Treatment) obj;
		return Objects.equals(id, other.id);
	}

	
}
