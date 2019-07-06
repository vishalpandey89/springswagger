package etl.demo.model;

public class InventoryGroup {
	private Double quantity;
	private String brandName;
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public InventoryGroup(String brandName,Double quantity) {
		
		this.brandName = brandName;
		this.quantity = quantity;
	}

	public InventoryGroup() {
	}
	
}
