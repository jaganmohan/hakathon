package smb.inv.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class FetchProductDetails {
	
	@Autowired
	private DataSource dataSource;
	Connection conn = null;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Product fetchProductDetails(String product, ProductBy productBY) throws SQLException{
		
		Product productDet = new Product();
		
		conn = dataSource.getConnection();
		String query = "Select * from ";
		if(productBY == ProductBy.ID)
			query = query+"product";
		else
			query = query+"productInfo";
		
		return productDet;
		
	}

}
