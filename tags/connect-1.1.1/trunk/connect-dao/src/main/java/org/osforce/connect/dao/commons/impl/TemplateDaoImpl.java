package org.osforce.connect.dao.commons.impl;

import java.util.List;

import org.osforce.connect.dao.commons.TemplateDao;
import org.osforce.connect.entity.commons.Template;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 10:59:36 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class TemplateDaoImpl extends AbstractDao<Template>  
	implements TemplateDao {

	public TemplateDaoImpl() {
		super(Template.class);
	}
	
	static final String JPQL0 = "FROM Template AS t WHERE t.category.id = ?1 AND t.code = ?2";
	public Template findTemplate(Long categoryId, String templateCode) {
		Assert.notNull(categoryId);
		Assert.notNull(templateCode);
		return findOne(JPQL0, categoryId, templateCode);
	}
	
	static final String JPQL1 = "FROM Template AS t WHERE t.category.site.id = ?1 ORDER BY t.code";
	public List<Template> findTemplateList(Long siteId) {
		return findList(JPQL1, siteId);
	}
	
	public Page<Template> findTemplatePage(Page<Template> page, Long siteId) {
		return findPage(page, JPQL1, siteId);
	}
}
