package vetApp.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import vetApp.enumeration.ReasonOfRemoval;

@Entity
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private LocalDate birthDate;
	
	@Column(nullable = false)
	private String race;
	
	@Column(nullable = false)
	private String furColor;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(nullable = false)
	private LocalDate rabiesVacinationDate;
	
	@Column(nullable = false)
	private boolean spayed;
	
	@Column()
	private boolean registeredInCentralBase;
	
	@Column()
	private LocalDate removedFromCentralBase;
	
	@Enumerated(EnumType.STRING)
	private ReasonOfRemoval removalReason;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "microchip_id", referencedColumnName = "id")
	private MicroChip microChip;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pet_id" , referencedColumnName = "id")
	private PetPassport passport;
	
	@OneToMany
    @JoinTable(
        name = "pet_inflicked_injury",
        joinColumns = @JoinColumn(name = "pet_id"),
        inverseJoinColumns = @JoinColumn(name = "inflickedInjury_id")
    )
	private Set<InflictedInjury> inflictedInjuries = new HashSet<>();
	
	@OneToMany
    @JoinTable(
        name = "pet_treatment",
        joinColumns = @JoinColumn(name = "pet_id"),
        inverseJoinColumns = @JoinColumn(name = "treatment_id")
    )
	private Set<Treatment> treatments = new HashSet<>();
	
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	
}
