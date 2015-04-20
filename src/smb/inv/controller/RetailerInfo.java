package smb.inv.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import smb.inv.service.FetchInflowOutflowDetails;
import smb.inv.service.Product;
import smb.inv.service.ProductBy;
import smb.inv.service.SoldProductDetails;

@Controller
@RequestMapping("/retailer/*")
public class RetailerInfo {
	
	@Autowired
	private SoldProductDetails retailerDetails;
	private String soldproduct;
	
	
	@RequestMapping(value="/sold/{productId}")
	@ResponseBody
	public String soldProduct(@PathVariable("productId") Long productID, Model model){
		try {
			soldproduct = retailerDetails.soldProduct(productID.toString(), ProductBy.ID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return soldproduct;
	}
}
