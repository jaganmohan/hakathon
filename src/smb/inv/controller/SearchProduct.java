package smb.inv.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import smb.inv.service.Product;
import smb.inv.service.ProductBy;
import smb.inv.service.ProductDesc;

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

import smb.inv.service.FetchProductDetails;

@Controller
@RequestMapping("/search/*")
public class SearchProduct{
	
	@Autowired
	private FetchProductDetails fetchProduct;
	private ArrayList<Product> product;
	
	public FetchProductDetails getFetchProduct() {
		return fetchProduct;
	}

	public void setFetchProduct(FetchProductDetails fetchProduct) {
		this.fetchProduct = fetchProduct;
	}

	@ModelAttribute
	public void populateModel(Model model){
		model.addAttribute("pageTitle", "Hello! Welcome to SMB Inventory Management");
	}
	
	@RequestMapping(value="/productid/{productId}")
	@ResponseBody
	public ArrayList<Product> searchById(@PathVariable("productId") Long productID, Model model){
		
		try {
			product = fetchProduct.fetchProductDetails(productID.toString(), ProductBy.ID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
		/*model.addAttribute("product", productID);
		model.addAttribute("searchType", "Searching product by product id ...");	
		return "product";*/
	}
	
	@RequestMapping("/productname/{productName}")
	@ResponseBody
	public ArrayList<Product> searchByName(@PathVariable("productName") String productName, Model model){
		
		try {
			product = fetchProduct.fetchProductDetails(productName, ProductBy.NAME);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
		/*model.addAttribute("product", productName);
		model.addAttribute("searchType", "Searching product by product name ...");
		return "product";*/
	}
	
	@RequestMapping(value="/model/{modelNo}")
	@ResponseBody
	public ArrayList<Product> searchByModel(@PathVariable("modelNo") String modelNo, Model model){
		
		try {
			product = fetchProduct.fetchProductDetails(modelNo, ProductBy.MODEL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
		/*model.addAttribute("product", modelNo);	
		model.addAttribute("searchType", "Searching product by product model number ...");	
		return "product";*/
	}

	@RequestMapping(value="/productinfo/{model}", method=RequestMethod.POST)
	@ResponseBody
	public ProductDesc productDesc(@RequestBody Product product,@PathVariable("model") String modelNo, Model model ){
		ProductDesc desc = null;
		try {
			desc = fetchProduct.productDesc(modelNo, product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return desc;
	}
	
	@RequestMapping(value="/products/{productname}", method=RequestMethod.POST)
	@ResponseBody
	
		public ArrayList<Product> products(@PathVariable("productName") String productName, Model model){
		ArrayList<Product> productDetails = null;
		try {
			productDetails = fetchProduct.products();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productDetails;
	}
}
