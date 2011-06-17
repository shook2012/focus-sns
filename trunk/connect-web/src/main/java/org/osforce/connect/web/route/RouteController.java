package org.osforce.connect.web.route;

import org.osforce.connect.entity.system.ProjectFeature;
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
		return "page:dashboards?path=/home";
	}
	
	@RequestMapping(value={"/login"}, method=RequestMethod.GET)
	public String route10() {
		return "page:system/login";
	}
	
	@RequestMapping(value={"/register"}, method=RequestMethod.GET)
	public String route11() {
		return "page:system/register";
	}
	
	@RequestMapping(value={"/logout"}, method=RequestMethod.GET)
	public String route20() {
		return "forward:system/user/logout-action";
	}
	
	@RequestMapping(value={"/search/profile"}, method=RequestMethod.GET)
	public String route30() {
		return "page:search/profile-result";
	}
	
	@RequestMapping(value={"/{categoryCode}"}, method=RequestMethod.GET)
	public String route90(@PathVariable String categoryCode, WebRequest request) {
		request.setAttribute("categoryCode", categoryCode, WebRequest.SCOPE_REQUEST);
		return String.format("page:dashboards?path=/%s", categoryCode);
	}
	
	@RequestMapping(value={"/{categoryCode}/{mode}"}, method=RequestMethod.GET)
	public String route91(@PathVariable String categoryCode,
			@PathVariable String mode, WebRequest request) {
		request.setAttribute("mode", mode, WebRequest.SCOPE_REQUEST);
		request.setAttribute("categoryCode", categoryCode, WebRequest.SCOPE_REQUEST);
		return String.format("page:dashboards?path=/%s", categoryCode);
	}
	
	@RequestMapping(value={"/{categoryCode}/form"}, method=RequestMethod.GET)
	public String route92(@PathVariable String categoryCode, WebRequest request) {
		request.setAttribute("categoryCode", categoryCode, WebRequest.SCOPE_REQUEST);
		return String.format("page:system/project-form", categoryCode);
	}
	
	@RequestMapping(value={"/app/user/login"}, method=RequestMethod.GET)
	public String route98(WebRequest request) {
		request.setAttribute("popup", Boolean.TRUE, WebRequest.SCOPE_REQUEST);
		return "page:apps/login-form";
	}
	
	@RequestMapping(value={"/app/message/form"}, method=RequestMethod.GET)
	public String route99(WebRequest request) {
		request.setAttribute("popup", Boolean.TRUE, WebRequest.SCOPE_REQUEST);
		return "page:apps/message-form";
	}
	//  ----------------------------------------------- Custom Pages ------------------------------------------------   //
	
	//  ----------------------------------------------- System Module ------------------------------------------------   //
	@RequestMapping(value={"/system", "/system/welcome"}, method=RequestMethod.GET)
	public String route100() {
		return "page:system/welcome";
	}

	@RequestMapping(value={"/system/site/{mode}"})
	public String route101(@PathVariable String mode) {
		return String.format("page:system/site-%s", mode);
	}
	
	@RequestMapping(value={"/system/resource/{mode}"}, method=RequestMethod.GET)
	public String route102(@PathVariable String mode) {
		return String.format("page:system/resource-%s", mode);
	}
	
	@RequestMapping(value={"/system/category/{mode}"}, method=RequestMethod.GET)
	public String route103(@PathVariable String mode) {
		return String.format("page:system/category-%s", mode);
	}
	
	@RequestMapping(value={"/system/template/{mode}"}, method=RequestMethod.GET)
	public String route104(@PathVariable String mode) {
		return String.format("page:system/template-%s", mode);
	}
	
	@RequestMapping(value={"/system/user/{mode}"}, method=RequestMethod.GET)
	public String route105(@PathVariable String mode) {
		return String.format("page:system/user-%s", mode);
	}
	
	@RequestMapping(value={"/system/role/{mode}"}, method=RequestMethod.GET)
	public String route106(@PathVariable String mode) {
		return String.format("page:system/role-%s", mode);
	}
	
	@RequestMapping(value={"/system/permission/{mode}"}, method=RequestMethod.GET)
	public String route107(@PathVariable String mode) {
		return String.format("page:system/permission-%s", mode);
	}
	//  ----------------------------------------------- System Module ------------------------------------------------  //
	
	//  ----------------------------------------------- Profile Module ------------------------------------------------  //
	@RequestMapping(value={"/{uniqueId}/profile"}, method=RequestMethod.GET)
	public String route120(@PathVariable String uniqueId, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				"profile", WebRequest.SCOPE_REQUEST);
		return "page:profile/profile-detail";
	}
	
	@RequestMapping(value={"/{uniqueId}/profile/form"}, method=RequestMethod.GET)
	public String route121(@PathVariable String uniqueId, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_PROFILE, WebRequest.SCOPE_REQUEST);
		return "page:profile/profile-form";
	}
	//  ----------------------------------------------- Profile Module ------------------------------------------------  //
	
	//  ------------------------------------------- Knowledge Module ---------------------------------------------  //
	@RequestMapping(value={"/{uniqueId}/knowledge"}, method=RequestMethod.GET)
	public String route140(@PathVariable String uniqueId, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_KNOWLEDGE, WebRequest.SCOPE_REQUEST);
		return "page:knowledge/question-list";
	}
	
	@RequestMapping(value={"/{uniqueId}/knowledge/question/{mode}"}, method=RequestMethod.GET)
	public String route141(@PathVariable String uniqueId, 
			@PathVariable String mode, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_KNOWLEDGE, WebRequest.SCOPE_REQUEST);
		return String.format("page:knowledge/question-%s", mode);
	}
	
	@RequestMapping(value={"/{uniqueId}/knowledge/answer/{mode}"}, method=RequestMethod.GET)
	public String route142(@PathVariable String uniqueId, 
			@PathVariable String mode, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_KNOWLEDGE, WebRequest.SCOPE_REQUEST);
		return String.format("page:knowledge/answer-%s", mode);
	}
	//  ------------------------------------------- Knowledge Module ---------------------------------------------  //
	
	//  -------------------------------------------- Calendar Module -----------------------------------------------  //
	@RequestMapping(value={"/{uniqueId}/calendar"}, method=RequestMethod.GET)
	public String route160(@PathVariable String uniqueId, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_CALENDAR, WebRequest.SCOPE_REQUEST);
		return "page:calendar/event-list";
	}
	
	@RequestMapping(value={"/{uniqueId}/calendar/event/{mode}"}, method=RequestMethod.GET)
	public String route161(@PathVariable String uniqueId, 
			@PathVariable  String mode, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_CALENDAR, WebRequest.SCOPE_REQUEST);
		return String.format("page:calendar/event-%s", mode);
	}
	//  -------------------------------------------- Calendar Module -----------------------------------------------  //
	
	//  ----------------------------------------------- Blog Module --------------------------------------------------  //
	@RequestMapping(value={"/{uniqueId}/blog"}, method=RequestMethod.GET)
	public String route180(@PathVariable String uniqueId, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_BLOG, WebRequest.SCOPE_REQUEST);
		return "page:blog/post-list";
	}
	
	@RequestMapping(value={"/{uniqueId}/blog/post/{mode}"}, method=RequestMethod.GET)
	public String route181(@PathVariable String uniqueId, 
			@PathVariable String mode, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_BLOG, WebRequest.SCOPE_REQUEST);
		return String.format("page:blog/post-%s", mode);
	}
	
	@RequestMapping(value={"/{uniqueId}/blog/category/{mode}"}, method=RequestMethod.GET)
	public String route182(@PathVariable String uniqueId, 
			@PathVariable String mode, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_BLOG, WebRequest.SCOPE_REQUEST);
		return String.format("page:blog/category-%s", mode);
	}
	//  ----------------------------------------------- Blog Module --------------------------------------------------  //
	
	//  --------------------------------------------- Gallery Module -------------------------------------------------  //
	@RequestMapping(value={"/{uniqueId}/gallery"}, method=RequestMethod.GET)
	public String route200(@PathVariable String uniqueId, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_GALLERY, WebRequest.SCOPE_REQUEST);
		return "page:gallery/album-list";
	}
	
	@RequestMapping(value={"/{uniqueId}/gallery/album/{mode}"}, method=RequestMethod.GET)
	public String route201(@PathVariable String uniqueId, 
			@PathVariable String mode, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_GALLERY, WebRequest.SCOPE_REQUEST);
		return String.format("page:gallery/album-%s", mode);
	}
	
	@RequestMapping(value={"/{uniqueId}/gallery/photo/{mode}"}, method=RequestMethod.GET)
	public String route202(@PathVariable String uniqueId, 
			@PathVariable String mode, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_GALLERY, WebRequest.SCOPE_REQUEST);
		return String.format("page:gallery/photo-%s", mode);
	}
	//  --------------------------------------------- Gallery Module -------------------------------------------------  //
	
	//  ------------------------------------------ Discussion Module ----------------------------------------------  //
	@RequestMapping(value={"/{uniqueId}/discussion"}, method=RequestMethod.GET)
	public String route220(@PathVariable String uniqueId, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_DISCUSSION, WebRequest.SCOPE_REQUEST);
		return "page:discussion/forum-list";
	}
	
	@RequestMapping(value={"/{uniqueId}/discussion/forum/{mode}"}, method=RequestMethod.GET)
	public String route221(@PathVariable String uniqueId, 
			@PathVariable String mode, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_DISCUSSION, WebRequest.SCOPE_REQUEST);
		return String.format("page:discussion/forum-%s", mode);
	}
	
	@RequestMapping(value={"/{uniqueId}/discussion/topic/{mode}"}, method=RequestMethod.GET)
	public String route222(@PathVariable String uniqueId, 
			@PathVariable String mode, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_DISCUSSION, WebRequest.SCOPE_REQUEST);
		return String.format("page:discussion/topic-%s", mode);
	}
	
	@RequestMapping(value={"/{uniqueId}/discussion/reply/{mode}"}, method=RequestMethod.GET)
	public String route223(@PathVariable String uniqueId, 
			@PathVariable String mode, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_DISCUSSION, WebRequest.SCOPE_REQUEST);
		return String.format("page:discussion/reply-%s", mode);
	}
	//  ------------------------------------------ Discussion Module ----------------------------------------------  //
	
	//  ------------------------------------------- Document Module ----------------------------------------------  //
	@RequestMapping(value={"/{uniqueId}/document"}, method=RequestMethod.GET)
	public String route240(@PathVariable String uniqueId, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_DOCUMENT, WebRequest.SCOPE_REQUEST);
		return "page:document/file-list";
	}
	
	@RequestMapping(value={"/{uniqueId}/document/file/{mode}"}, method=RequestMethod.GET)
	public String route241(@PathVariable String uniqueId, 
			@PathVariable String mode, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_DOCUMENT, WebRequest.SCOPE_REQUEST);
		return String.format("page:document/file-%s", mode);
	}
	
	@RequestMapping(value={"/{uniqueId}/document/folder/{mode}"}, method=RequestMethod.GET)
	public String route242(@PathVariable String uniqueId, 
			@PathVariable String mode, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_DOCUMENT, WebRequest.SCOPE_REQUEST);
		return String.format("page:document/folder-%s", mode);
	}
	//  ------------------------------------------- Document Module ----------------------------------------------  //
	
	//  ---------------------------------------------- Team Module -------------------------------------------------  //
	@RequestMapping(value={"/{uniqueId}/team"}, method=RequestMethod.GET)
	public String route260(@PathVariable String uniqueId, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_TEAM, WebRequest.SCOPE_REQUEST);
		return "page:team/member-list";
	}
	
	@RequestMapping(value={"/{uniqueId}/team/member/{mode}"}, method=RequestMethod.GET)
	public String route260(@PathVariable String uniqueId, 
			@PathVariable String mode, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_TEAM, WebRequest.SCOPE_REQUEST);
		return String.format("page:team/member-%s", mode);
	}
	//  ---------------------------------------------- Team Module -------------------------------------------------  //
	
	// -------------------------------------------- Message Module -----------------------------------------------  //
	@RequestMapping(value={"/{uniqueId}/message"}, method=RequestMethod.GET)
	public String route300(@PathVariable String uniqueId, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_MESSAGE, WebRequest.SCOPE_REQUEST);
		return "page:message/message-inbox";
	}
	
	@RequestMapping(value={"/{uniqueId}/message/{mode}"}, method=RequestMethod.GET)
	public String route301(@PathVariable String uniqueId, 
			@PathVariable String mode, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_MESSAGE, WebRequest.SCOPE_REQUEST);
		return String.format("page:message/message-%s", mode);
	}
	
	@RequestMapping(value={"/{uniqueId}/message/{box}/{mode}"}, method=RequestMethod.GET)
	public String route302(@PathVariable String uniqueId, 
			@PathVariable String box, @PathVariable String mode, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_MESSAGE, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute("box", box, WebRequest.SCOPE_REQUEST);
		return String.format("page:message/message-%s", mode);
	}
	// -------------------------------------------- Message Module -----------------------------------------------  //
	
	//  ---------------------------------------------- Admin Module -------------------------------------------------  //
	@RequestMapping(value={"/{uniqueId}/admin"}, method=RequestMethod.GET)
	public String route280(@PathVariable String uniqueId, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_ADMIN, WebRequest.SCOPE_REQUEST);
		return "page:admin/welcome";
	}
	
	@RequestMapping(value={"/{uniqueId}/admin/{target}/form"}, method=RequestMethod.GET)
	public String route281(@PathVariable String uniqueId, 
			@PathVariable String target, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_ADMIN, WebRequest.SCOPE_REQUEST);
		return String.format("page:admin/%s-form", target);
	}
	
	//  ---------------------------------------------- Admin Module -------------------------------------------------  //

	//  ---------------------------------------------- List Module -------------------------------------------------  //
	@RequestMapping(value={"/{uniqueId}/list"})
	public String route320(@PathVariable String uniqueId, WebRequest webRequest) {
		webRequest.setAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE, 
				uniqueId, WebRequest.SCOPE_REQUEST);
		webRequest.setAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, 
				ProjectFeature.FEATURE_LIST, WebRequest.SCOPE_REQUEST);
		return "page:list/link-list";
	}
	//  ---------------------------------------------- List Module -------------------------------------------------  //
}
