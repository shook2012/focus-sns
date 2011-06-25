package org.osforce.connect.dao.system.impl;

import java.util.List;

import org.osforce.connect.dao.system.ResourceDao;
import org.osforce.connect.entity.system.Resource;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 10:55:09 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ResourceDaoImpl extends AbstractDao<Resource> 
	implements ResourceDao {

	public ResourceDaoImpl() {
		super(Resource.class);
	}
	
	static final String JPQL0 = "FROM Resource AS r WHERE r.code = ?1";
	public Resource findResource(String code) {
		return findOne(JPQL0, code);
	}
	
	static final String JPQL1 = "FROM Resource AS r ORDER BY r.code";
	public List<Resource> findResourceList() {
		return findList(JPQL1);
	}
	
	public Page<Resource> findResourcePage(Page<Resource> page) {
		return findPage(page, JPQL1);
	}
}
