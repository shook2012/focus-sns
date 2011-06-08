package org.osforce.connect.dao.commons.impl;

import org.osforce.connect.dao.commons.LinkDao;
import org.osforce.connect.entity.commons.Link;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 22, 2011 - 2:11:45 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class LinkDaoImpl extends AbstractDao<Link> implements LinkDao {

	protected LinkDaoImpl() {
		super(Link.class);
	}
	
	static final String JPQL0 = "FROM Link AS l WHERE l.from.id = ?1 AND l.toId = ?2 AND l.entity = ?3";
	public Link findLink(Long fromId, Long toId, String entity) {
		return findOne(JPQL0, fromId, toId, entity);
	}
	
	static final String JPQL1 = "FROM Link AS l WHERE  l.entity = ?1 %s";
	public Page<Link> findLinkPage(Page<Link> page, Long fromId, Long toId,
			String entity) {
		if(fromId!=null) {
			return findPage(page, String.format(JPQL1, "AND l.from.id = ?2"), fromId);
		} else if(toId!=null) {
			return findPage(page, String.format(JPQL1, "AND l.toId = ?2"), toId);
		}
		return null;
	}

	static final String JPQL2 = "SELECT COUNT(*) FROM Link AS l WHERE l.entity = ?1 %s";
	public Long countLinks(String type, Long toId, String entity) {
		Assert.notNull(toId, "Paramter toId can not be null!");
		Assert.notNull(entity, "Paramter entity can not be null!");
		if(type!=null) {
			return count(String.format(JPQL2, "AND l.type = ?2 AND l.toId = ?3"), entity, type, toId);
		} else {
			return count(String.format(JPQL2, "AND l.toId = ?3"), entity, toId);
		}
	}

}
