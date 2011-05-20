package org.osforce.connect.dao.system.impl;

import java.util.List;

import org.osforce.connect.dao.system.ThemeDao;
import org.osforce.connect.entity.system.Theme;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 28, 2011 - 4:11:21 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ThemeDaoImpl extends AbstractDao<Theme> implements ThemeDao  {

	protected ThemeDaoImpl() {
		super(Theme.class);
	}
	
	static final String JPQL0 = "FROM Theme AS t";
	public List<Theme> findThemeList() {
		return findList(JPQL0);
	}
	
	static final String JPQL1 = "FROM Theme AS t";
	public Page<Theme> findThemePage(Page<Theme> page) {
		return findPage(page, JPQL1);
	}

}
