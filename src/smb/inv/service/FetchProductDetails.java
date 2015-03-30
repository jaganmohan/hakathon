package smb.inv.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

public class FetchProductDetails {
	
	private DataSource dataSource;
	Connection conn = null;
	private String productDescFolder;
	
	public String getProductDescFolder() {
		return productDescFolder;
	}

	public void setProductDescFolder(String productDescFolder) {
		this.productDescFolder = productDescFolder;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
		conn = getDataSource().getConnection();
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public Connection getConnection(){
		return conn;
	}
	
	public ArrayList<Product> fetchProductDetails(String product, ProductBy productBY) throws SQLException{
		
		ArrayList<Product> productDet = new ArrayList<Product>();
		Statement stmt;
		String query = "Select * from ";
		if(productBY == ProductBy.ID)
			query = query+"product NATURAL JOIN productInfo where productID="+Long.parseLong(product);
		else if(productBY == ProductBy.MODEL)
			query = query+"productInfo NATURAL JOIN product where modelNo=\""+product+"\"";
		else
			query = query+"productInfo NATURAL JOIN product where productName LIKE \""+product+"%\"";
		stmt = getConnection().createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		Product p;
		while(result.next()){
			p = new Product();
			p.setModelNo(result.getString("modelNo"));
			p.setProductId(result.getLong("productID"));
			p.setInflowDate(result.getDate("inflowDate"));
			p.setOutflowDate(result.getDate("outflowDate"));
			p.setDescFileLoc(result.getString("descFileLoc"));
			p.setProductName(result.getString("productName"));
			productDet.add(p);
		}
		return productDet;
		
	}
	
	public ProductDesc productDesc(String model, Product product) throws SQLException{
		
		Statement s = getConnection().createStatement();
		String query = "Select * from "+model+"_specs";
		ResultSet result = s.executeQuery(query);
		ProductDesc desc = new ProductDesc();
		product.setSpecs(new HashMap<String,String>());
		while(result.next()){
			product.getSpecs().put(result.getString("specskey"), result.getString("specsvalue"));
		}
		desc.setProduct(product);
		BufferedReader read = null;
		try {
			read = Files.newBufferedReader(Paths.get(getProductDescFolder()+product.getDescFileLoc())
					, StandardCharsets.UTF_8);
			desc.setDesc(new ArrayList<String>());
			String line;
			while((line=read.readLine())!=null){
				desc.getDesc().add(line);
			}
		} catch (IOException e) {
			//TOODO log file not found exception message
		}
		
		return desc;
	}


public ArrayList<Product> products() throws SQLException{
	
	ArrayList<Product> productDet = new ArrayList<Product>();
	Statement s = getConnection().createStatement();
	String query = "Select * from product";
	ResultSet result = s.executeQuery(query);
	Product p;
	while(result.next()){
		p = new Product();
		p.setModelNo(result.getString("modelNo"));
		p.setProductId(result.getLong("productID"));
		p.setInflowDate(result.getDate("inflowDate"));
		p.setOutflowDate(result.getDate("outflowDate"));
		p.setDescFileLoc(result.getString("descFileLoc"));
		p.setProductName(result.getString("productName"));
		productDet.add(p);
	}
	return productDet;
}

}
