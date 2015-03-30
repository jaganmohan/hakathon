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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import javax.sql.DataSource;



public class SoldProductDetails {

	private DataSource dataSource;
	Connection conn = null;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
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
	
public String soldProduct(String product, ProductBy productBY) throws SQLException{
		
		Statement stmt;
		Date curDate = new Date();
		String query = "update product set outflowDate=\""+df.format(curDate)+"\" where productID="+Long.parseLong(product);
		int result;
		String soldProductRes="failed";
		
		stmt = getConnection().createStatement();
		result = stmt.executeUpdate(query);
		
		if (result == 1)
			soldProductRes = "sold";

		return soldProductRes;
		
	}
}
