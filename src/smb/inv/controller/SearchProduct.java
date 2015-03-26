package smb.inv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

@Controller
@RequestMapping("/search/*")
public class SearchProduct{
	
	@ModelAttribute
	public void populateModel(Model model){
		model.addAttribute("pageTitle", "Hello! Welcome to SMB Inventory Management");
	}
	
	@RequestMapping("/productid/{productId}")
	public String searchById(@PathVariable("productId") long productID, Model model){
		model.addAttribute("product", productID);		
		return "product";
	}
	
	@RequestMapping("/productname/{productName}")
	public String searchById(@PathVariable("productName") String productName, Model model){
		model.addAttribute("product", productName);		
		return "product";
	}
	
}
