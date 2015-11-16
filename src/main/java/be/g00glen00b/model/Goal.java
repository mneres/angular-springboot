package be.g00glen00b.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@NotNull
	@Size(max = 100)
	@Column(nullable = false)
	private String name;
	@NotNull
	@Column(nullable = false)
	private String description;
	@Temporal(TemporalType.DATE)
	@Column(name = "planned_date")
	private Date plannedDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "created_at")
	public Date createdAt;
	
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
	public Date getPlannedDate() {
		return plannedDate;
	}
	public void setPlannedDate(Date plannedDate) {
		this.plannedDate = plannedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
