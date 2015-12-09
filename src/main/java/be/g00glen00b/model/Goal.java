package be.g00glen00b.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Goal{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="GOAL_ID")
	private Integer id;
	@OneToOne
	@JoinColumn(name="USER_ID", nullable=true, updatable=false)
	private User user;
	@NotNull
	@Size(max = 100)
	@Column(nullable = false)
	private String name;
	@NotNull
	@Column(nullable = false)
	private String category;
	@Temporal(TemporalType.DATE)
	@Column(name = "created_at")
	public Date createdAt;
	@OneToMany(orphanRemoval=true)
    @JoinColumn(name="REQUERIMENTS_ID")
    public Collection<Requirement> requirements  = new ArrayList<Requirement>();

	@PrePersist
	void createdAt() {
	    this.createdAt = new Date();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user){
		this.user = user;
	}
	public Collection<Requirement> getRequeriments() {
		return requirements;
	}
	public boolean addRequeriment(Requirement requirement){
		try{
			if(!requirement.getValue().isEmpty() && !requirement.getCategory().isEmpty()){
				this.requirements.add(requirement);
			}else{
				return false;
			}
			
		}catch(Exception e){
			return false;
		}
		return true;
	}
}
