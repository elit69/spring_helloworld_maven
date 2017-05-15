package com.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.webjars.WebJarAssetLocator;

@Controller
public class AssetController {
	
	@ResponseBody
	@RequestMapping("/webjars/{webjar}/**")
	public ResponseEntity<ClassPathResource> locateWebjarAsset(@PathVariable String webjar, HttpServletRequest request) {
	    try {
	    	WebJarAssetLocator locator = new WebJarAssetLocator();
	        String mvcPrefix = "/webjars/" + webjar + "/"; // This prefix must match the mapping path!
	        String mvcPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	        String fullPath = locator.getFullPath(webjar, mvcPath.substring(mvcPrefix.length()));
	        return new ResponseEntity<ClassPathResource>(new ClassPathResource(fullPath), HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
}
