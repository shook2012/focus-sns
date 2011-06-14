package org.osforce.connect.dao.commons.impl;

import java.util.List;

import org.osforce.connect.dao.commons.TagDao;
import org.osforce.connect.entity.commons.Tag;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 9, 2011 - 9:16:46 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class TagDaoImpl extends AbstractDao<Tag>
	implements TagDao {

	public TagDaoImpl() {
		super(Tag.class);
	}
	
	static final String JPQL0 = "FROM Tag AS t WHERE t.linkedId = ?1 AND t.entity = ?2";
	public List<Tag> findTagList(Long linkedId, String entity) {
		return findList(JPQL0, linkedId, entity);
	}
	
	static final String JPQL1 = "FROM Tag AS t WHERE t.name LIKE ?1";
	public Page<Tag> findTagPage(Page<Tag> page, String startWith) {
		return findPage(page, JPQL1, startWith+"%");
	}
	
}
