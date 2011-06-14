package org.osforce.connect.dao.system.impl;

import java.util.List;

import org.osforce.connect.dao.system.ProjectCategoryDao;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:04:44 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ProjectCategoryDaoImpl extends AbstractDao<ProjectCategory>
	implements ProjectCategoryDao {

	public ProjectCategoryDaoImpl() {
		super(ProjectCategory.class);
	}
	
	static final String JPQL0 = "FROM ProjectCategory pc WHERE pc.site.id = ?1 AND pc.code = ?2";
	public ProjectCategory findProjectCategory(Long siteId, String categoryCode) {
		return findOne(JPQL0, siteId, categoryCode);
	}
	
	static final String JPQL1 = "FROM ProjectCategory pc %s ORDER BY pc.level";
	public List<ProjectCategory> findProjectCategoryList(Long siteId, Long parentId) {
		if(parentId!=null && parentId!=null) {
			return findList(String.format(JPQL1, "WHERE pc.site.id = ?1 AND pc.parent.id = ?2"), siteId, parentId);
		} else if(siteId!=null) {
			return findList(String.format(JPQL1, "WHERE pc.site.id = ?1 AND pc.parent.id IS NULL"), siteId);
		} else if(parentId!=null) {
			return findList(String.format(JPQL1, "WHERE pc.parent.id = ?1"), parentId);
		} else {
			return findList(String.format(JPQL1, ""));
		}
	}
	
	static final String JPQL2 = "FROM ProjectCategory pc %s ORDER BY pc.code ASC";
	public Page<ProjectCategory> findProjectCategoryPage(
			Page<ProjectCategory> page, Long siteId, Long parentId) {
		if(siteId!=null && parentId!=null) {
			return findPage(page, String.format(JPQL2, "WHERE pc.site.id = ?1 AND pc.parent.id = ?2"), siteId, parentId);
		} else if(siteId!=null) {
			return findPage(page, String.format(JPQL2, "WHERE pc.site.id = ?1 AND pc.parent.id IS NULL"), siteId);
		} else if(parentId!=null){
			return findPage(page, String.format(JPQL2, "WHERE pc.parent.id = ?1"), parentId);
		} else {
			return findPage(page, String.format(JPQL2, ""));
		}
	}
	
}
