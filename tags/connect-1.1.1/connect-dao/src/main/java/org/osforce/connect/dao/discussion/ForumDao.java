package org.osforce.connect.dao.discussion;

import java.util.List;

import org.osforce.connect.entity.discussion.Forum;
import org.osforce.spring4me.dao.BaseDao;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:09:20 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ForumDao extends BaseDao<Forum> {

	/**
	 * Find forums by ...
	 * @param projectId
	 * @return
	 */
	List<Forum> findForumList(Long projectId);

}
