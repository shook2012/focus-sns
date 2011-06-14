package org.osforce.connect.dao.commons.impl;

import java.util.List;

import org.osforce.connect.dao.commons.CommentDao;
import org.osforce.connect.entity.commons.Comment;
import org.osforce.spring4me.dao.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 10:58:33 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class CommentDaoImpl extends AbstractDao<Comment> 
	implements CommentDao {

	public CommentDaoImpl() {
		super(Comment.class);
	}
	
	static final String JPQL0 = "FROM Comment AS c WHERE c.linkedId = ?1 AND c.entity = ?2";
	public List<Comment> findCommentLIst(Long linkedId, String entity) {
		return findList(JPQL0, linkedId, entity);
	}
	
	static final String JPQL1 = "SELECT COUNT (*) FROM Comment AS c WHERE c.linkedId = ?1 AND c.entity = ?2";
	public Long countComments(Long linkedId, String entity) {
		return count(JPQL1, linkedId, entity);
	}
	
}
