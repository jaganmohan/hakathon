package smb.inv.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InflowOutflow {

	@RequestMapping(value="/inflow", method=RequestMethod.GET)
	public String inflow(@RequestParam Map<String,String> params, Model model){
		return "inflow";
	}
	
	@RequestMapping(value="/outflow", method=RequestMethod.GET)
	public String outflow(@RequestParam Map<String,String> params, Model model){
		return "outflow";
	}
	
}
