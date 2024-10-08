package vetApp.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import vetApp.enumeration.Roles;

@Entity
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column( unique = true, nullable = false)
    private String eMail;

    @Column( nullable = false)
    private String firstName;

    @Column( nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;
    
    @OneToMany(mappedBy = "user" , fetch = FetchType.EAGER)
    private List<Pet> pets = new ArrayList<>();
    
    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
    private List<PetPassport> passports = new ArrayList<>();
    
    @OneToOne
    private Address address;

    public User(){

    }

    public User(String userName, String eMail, String firstName, String lastName, String password, Roles role, List<Pet> pets, Address address, List<PetPassport> passports) {
        this.userName = userName;
        this.eMail = eMail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.pets = pets;
        this.address = address;
        this.passports = passports;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	//equals method where we handle nulls manually
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", first name=" + firstName + ", last name=" + lastName + "]";
    }

}
