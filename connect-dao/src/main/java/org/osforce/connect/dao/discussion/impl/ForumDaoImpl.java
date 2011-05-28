package org.osforce.connect.dao.discussion.impl;

import java.util.List;

import org.osforce.connect.dao.discussion.ForumDao;
import org.osforce.connect.entity.discussion.Forum;
import org.osforce.spring4me.dao.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:11:25 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ForumDaoImpl extends AbstractDao<Forum> 
	implements ForumDao {

	public ForumDaoImpl() {
		super(Forum.class);
	}
	
	static final String JPQL0 = "FROM Forum AS f WHERE f.project.id = ?1";
	public List<Forum> findForumList(Long projectId) {
		return findList(JPQL0, projectId);
	}
}
