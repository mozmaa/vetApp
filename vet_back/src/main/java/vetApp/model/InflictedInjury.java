package vetApp.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.*;

import vetApp.enumeration.Attacked;

@Entity
public class InflictedInjury {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private boolean fatalInjury;
	
	@Column(nullable = false)
	private LocalDate dateOfAttack;
	
	@Column(nullable = false)
	private String injuryDescription;
	
	@Enumerated(EnumType.STRING)
	private Attacked victimOfAttack;
	
	public InflictedInjury() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isFatalInjury() {
		return fatalInjury;
	}

	public void setFatalInjury(boolean fatalInjury) {
		this.fatalInjury = fatalInjury;
	}

	public LocalDate getDateOfAttack() {
		return dateOfAttack;
	}

	public void setDateOfAttack(LocalDate dateOfAttack) {
		this.dateOfAttack = dateOfAttack;
	}

	public String getInjuryDescription() {
		return injuryDescription;
	}

	public void setInjuryDescription(String injuryDescription) {
		this.injuryDescription = injuryDescription;
	}

	public Attacked getVictimOfAttack() {
		return victimOfAttack;
	}

	public void setVictimOfAttack(Attacked victimOfAttack) {
		this.victimOfAttack = victimOfAttack;
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
		InflictedInjury other = (InflictedInjury) obj;
		return Objects.equals(id, other.id);
	};
	
	
	
}
