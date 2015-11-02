package be.g00glen00b.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	/*@ManyToOne
	private MyList list;*/
	@NotNull
	@Size(max = 100)
	@Column(nullable = false)
	private String name;
	@Temporal(TemporalType.DATE)
	private Date plannedDate;
	@Column(nullable = false)
	private String description;
	
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
}
