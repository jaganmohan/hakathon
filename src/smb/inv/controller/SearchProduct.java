package smb.inv.controller;

import smb.inv.service.Product;
import smb.inv.service.ProductBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import smb.inv.service.FetchProductDetails;

@Controller
@RequestMapping("/search/*")
public class SearchProduct{
	
	@Autowired
	private FetchProductDetails fetchProduct;
	
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
	
	@RequestMapping(value="/productid/{productId}", produces="application/json")
	public Product searchById(@PathVariable("productId") Long productID, Model model){
		
		return fetchProduct.fetchProductDetails(productID.toString(), ProductBy.ID);
		/*model.addAttribute("product", productID);
		model.addAttribute("searchType", "Searching product by product id ...");	
		return "product";*/
	}
	
	@RequestMapping("/productname/{productName}")
	public Product searchByName(@PathVariable("productName") String productName, Model model){
		
		return fetchProduct.fetchProductDetails(productName, ProductBy.NAME);
		/*model.addAttribute("product", productName);
		model.addAttribute("searchType", "Searching product by product name ...");
		return "product";*/
	}
	
	@RequestMapping("/model/{modelNo}")
	public Product searchByModel(@PathVariable("modelNo") String modelNo, Model model){
		
		return fetchProduct.fetchProductDetails(modelNo, ProductBy.MODEL);
		/*model.addAttribute("product", modelNo);	
		model.addAttribute("searchType", "Searching product by product model number ...");	
		return "product";*/
	}
	
}
