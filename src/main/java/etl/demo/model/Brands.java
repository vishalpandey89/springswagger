package etl.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "brands")
@ApiModel(description="Brands Name")
public class Brands implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	@Id
	@ApiModelProperty(notes = "The database generated brand ID")
	@Column(name = "brand_id")
	private long brandId;
	
	@ApiModelProperty(notes = "The brand name")
	@Column(name = "name", nullable = false)
	private String name;

	//Start setter and getter methods
	
	public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
