package com.app.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import org.webjars.WebJarAssetLocator;

@Controller
public class PageController {
		@RequestMapping(value="/", method = RequestMethod.GET)
		public String home(ModelMap m) {
			m.addAttribute("message", "Hello World!");
			return "home";
		}
		
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
		

		@RequestMapping(value = "savefile", method = RequestMethod.POST)
		public String saveimage(@RequestParam("textfile") CommonsMultipartFile file, HttpSession session) throws Exception {

			String path = session.getServletContext().getRealPath("");
			System.out.println(path);
			String filename = file.getOriginalFilename();
			String fullPath = path + File.separator + filename;

			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fullPath)));
			stream.write(bytes);
			stream.flush();
			stream.close();

			return "success";
		}
}
