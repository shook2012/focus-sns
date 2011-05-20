package org.osforce.connect.dao.discussion.impl;

import org.osforce.connect.dao.discussion.TopicDao;
import org.osforce.connect.entity.discussion.Topic;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:14:19 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class TopicDaoImpl extends AbstractDao<Topic> 
	implements TopicDao {

	public TopicDaoImpl() {
		super(Topic.class);
	}

	static final String JPQL0 = "FROM Topic AS t %s ORDER BY t.entered";
	public Page<Topic> findTopicPage(Page<Topic> page, Long projectId, Long forumId) {
		if(projectId!=null) {
			return findPage(page, String.format(JPQL0, "WHERE t.forum.project.id = ?1"), projectId);
		} else if(forumId!=null) {
			return findPage(page, String.format(JPQL0, "WHERE t.forum.id = ?1"), forumId);
		}
		return null;
	}
}
