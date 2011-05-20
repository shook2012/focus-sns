package org.osforce.connect.web.route;

import org.osforce.connect.web.AttributeKeys;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 18, 2011 - 9:31:37 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
public class RouteController {

	//  ----------------------------------------------- Custom Pages ------------------------------------------------   //
	@RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
	public String route0() {
		return "page:/home";
	}
	
	@RequestMapping(value={"/login"}, method=RequestMethod.GET)
	public String route10() {
		return "page:/login";
	}
	
	@RequestMapping(value={"/logout"}, method=RequestMethod.GET)
	public String route20() {
		return "forward:/system/user/logout-action";
	}
	
	@RequestMapping(value={"/{categoryCode}"}, method=RequestMethod.GET)
	public String route90(@PathVariable String categoryCode) {
		return "page:/" + categoryCode;
	}
	//  ----------------------------------------------- Custom Pages ------------------------------------------------   //
	
	//  ----------------------------------------------- System Module ------------------------------------------------   //
	@RequestMapping(value={"/system", "/system/welcome"}, method=RequestMethod.GET)
	public String route100() {
		return "page:/system/welcome";
	}

	@RequestMapping(value={"/system/site/{mode}"})
	public String route101(@PathVariable String mode) {
		return String.format("page:/system/site-%s", mode);
	}
	
	@RequestMapping(value={"/system/resource/{mode}"}, method=RequestMethod.GET)
	public String route102(@PathVariable String mode) {
		return String.format("page:/system/resource-%s", mode);
	}
	
	@RequestMapping(value={"/system/category/{mode}"}, method=RequestMethod.GET)
	public String route103(@PathVariable String mode) {
		return String.format("page:/system/category-%s", mode);
	}
	
	@RequestMapping(value={"/system/template/{mode}"}, method=RequestMethod.GET)
	public String route104(@PathVariable String mode) {
		return String.format("page:/system/template-%s", mode);
	}
	
	@RequestMapping(value={"/system/user/{mode}"}, method=RequestMethod.GET)
	public String route105(@PathVariable String mode) {
		return String.format("page:/system/user-%s", mode);
	}
	
	@RequestMapping(value={"/system/role/{mode}"}, method=RequestMethod.GET)
	public String route106(@PathVariable String mode) {
		return String.format("page:/system/role-%s", mode);
	}
	
	@RequestMapping(value={"/system/permission/{mode}"}, method=RequestMethod.GET)
	public String route107(@PathVariable String mode) {
		return String.format("page:/system/permission-%s", mode);
	}
	//  ----------------------------------------------- System Module ------------------------------------------------  //
	
	//  ----------------------------------------------- Profile Module ------------------------------------------------  //
	@RequestMapping(value="/{uniqueId}/profile", method=RequestMethod.GET)
	public String route120(@PathVariable String uniqueId, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				"profile", WebRequest.SCOPE_REQUEST);
		return "page:/profile/profile-detail";
	}
	
	@RequestMapping(value="/{uniqueId}/profile/form", method=RequestMethod.GET)
	public String route121(@PathVariable String uniqueId, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				"profile", WebRequest.SCOPE_REQUEST);
		return "page:/profile/profile-form";
	}
	//  ----------------------------------------------- Profile Module ------------------------------------------------  //
}
