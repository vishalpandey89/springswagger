package etl.demo.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "brand_data")
@ApiModel(description="Brands data details")
@XmlRootElement
public class BrandData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(notes = "The database generated brand quantity ID")
	private long id;
	
	@NotNull(message = "Brand Id is required")
	@Column(name = "brand_id")
	private Long brandId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id", referencedColumnName = "brand_id", insertable = false, updatable = false)
	private Brands brand;
	
	@NotNull(message = "Quantity is required")
	@Column
	private Double quantity;
	
	@ApiModelProperty(notes = "Time Received")
	@NotNull(message = "Quantity is required")
	@Column(name = "time_received", length = 45)
	private Date timeReceived;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Brands getBrand() {
		return brand;
	}

	public void setBrand(Brands brand) {
		this.brand = brand;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Date getTimeReceived() {
		return timeReceived;
	}

	public void setTimeReceived(Date timeReceived) {
		this.timeReceived = timeReceived;
	}

	
	
	
	
}
