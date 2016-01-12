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

@Entity
public class Action {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="GOAL_ID")
	private Integer id;
	private String action;
	private boolean finished;
	@Temporal(TemporalType.DATE)
	@Column(name = "planning_date")
	private Date planningDate;
	private String resource;
	@Temporal(TemporalType.DATE)
	@Column(name = "created_at")
	private Date createdAt;

	@PrePersist
	void createdAt() {
	    this.createdAt = new Date();
	    this.finished = false;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public Date getPlanningDate() {
		return planningDate;
	}
	public void setPlanningDate(Date planningDate) {
		this.planningDate = planningDate;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
