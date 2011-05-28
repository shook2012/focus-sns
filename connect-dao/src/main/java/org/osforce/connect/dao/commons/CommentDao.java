package org.osforce.connect.dao.commons;

import java.util.List;

import org.osforce.connect.entity.commons.Comment;
import org.osforce.spring4me.dao.BaseDao;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 10:56:41 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface CommentDao extends BaseDao<Comment> {

	/**
	 * Find comments by ...
	 * @param linkedId
	 * @param entity
	 * @return
	 */
	List<Comment> findCommentLIst(Long linkedId, String entity);

	/**
	 * Count comments by ... 
	 * @param linkedId
	 * @param entity
	 * @return
	 */
	Long countComments(Long linkedId, String entity);

}
