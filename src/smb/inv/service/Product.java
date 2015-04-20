package smb.inv.service;

import java.util.Map;
import java.util.Date;

public class Product {

	private long productId;
	private String productName;
	private String modelNo;
	private Map<String, String> specs;
	private String productDesc;
	private Date inflowDate;
	private Date outflowDate;
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public Map<String, String> getSpecs() {
		return specs;
	}
	public void setSpecs(Map<String, String> specs) {
		this.specs = specs;
	}
	public String getDescFileLoc() {
		return productDesc;
	}
	public void setDescFileLoc(String productDesc) {
		this.productDesc = productDesc;
	}
	public Date getInflowDate() {
		return inflowDate;
	}
	public void setInflowDate(Date inflowDate) {
		this.inflowDate = inflowDate;
	}
	public Date getOutflowDate() {
		return outflowDate;
	}
	public void setOutflowDate(Date outflowDate) {
		this.outflowDate = outflowDate;
	}
	
	@Override
	public String toString() {
		String outflowDt = getOutflowDate()==null?"Not Sold":getOutflowDate().toString();
		// TODO Auto-generated method stub
		return "ProductId: "+getProductId()+", Product Name: "+getProductName()+", "+
				"Model Number: "+getModelNo()+", Inflow Date: "+getInflowDate()+
				", Outflow Date: "+ outflowDt;
	}
	
}
