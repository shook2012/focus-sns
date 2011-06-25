package org.osforce.connect.web.module.system;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.Search;
import org.osforce.connect.Application;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 18, 2011 - 5:27:35 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/system")
public class SystemWidget {

	private EntityManager entityManager;
	
	public SystemWidget() {
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@RequestMapping(value="menu-view")
	public String doMenuView() {
		return "system/menu";
	}
	
	@RequestMapping(value="info-view")
	public String doInfoView(Model model) {
		model.addAttribute(AttributeKeys.APPLICATION_KEY_READABLE, Application.getInstance());
		return "system/info";
	}
	
	@RequestMapping(value={"/search/index"}, method=RequestMethod.GET)
	public @ResponseBody Object doIndexAction() {
		Search.getFullTextEntityManager(entityManager).createIndexer().start();
		return "success";
	}
	
}
