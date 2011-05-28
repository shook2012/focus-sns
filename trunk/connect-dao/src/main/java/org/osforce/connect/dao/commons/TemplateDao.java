package org.osforce.connect.dao.commons;

import java.util.List;

import org.osforce.connect.entity.commons.Template;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 10:57:59 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface TemplateDao extends BaseDao<Template> {

	/**
	 * Find template by category id and template code
	 * @param categoryId
	 * @param templateCode
	 * @return
	 */
	Template findTemplate(Long categoryId, String templateCode);

	/**
	 * Find template list by site id
	 * @param siteId
	 * @return
	 */
	List<Template> findTemplateList(Long siteId);
	
	/**
	 * Find template page by site id
	 * @param page
	 * @param siteId
	 * @return
	 */
	Page<Template> findTemplatePage(Page<Template> page, Long siteId);

	
	
}
