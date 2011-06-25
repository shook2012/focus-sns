package org.osforce.connect.dao.knowledge.impl;

import org.osforce.connect.dao.knowledge.AnswerDao;
import org.osforce.connect.entity.knowledge.Answer;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:31:48 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class AnswerDaoImpl extends AbstractDao<Answer> 
	implements AnswerDao {

	public AnswerDaoImpl() {
		super(Answer.class);
	}
	
	static final String JPQL0 = "FROM Answer AS a WHERE a.question.id = ?1";
	public Page<Answer> findAnswerPage(Page<Answer> page, Long questionId) {
		return findPage(page, JPQL0, questionId);
	}
	
}
