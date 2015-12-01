package be.g00glen00b.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Requeriment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="REQUERIMENTS_ID")
	private Integer id;
	@NotNull
	@Column(nullable = false)
	private String category;
	@NotNull
	@Column(nullable = false)
	private String value;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
