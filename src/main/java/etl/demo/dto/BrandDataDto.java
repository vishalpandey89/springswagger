package etl.demo.dto;

import java.util.Date;

import etl.demo.model.Brands;

public class BrandDataDto {
	
	private long id;
	private Long brandId;
	private String brandName;
	private Double quantity;
	private Date timeReceived;
	

	//Start Setter and getter methods
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
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
}
