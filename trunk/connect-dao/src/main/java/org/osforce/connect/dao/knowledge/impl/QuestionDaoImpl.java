package org.osforce.connect.dao.knowledge.impl;

import org.osforce.connect.dao.knowledge.QuestionDao;
import org.osforce.connect.entity.knowledge.Question;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:29:47 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class QuestionDaoImpl extends AbstractDao<Question>
	implements QuestionDao {

	public QuestionDaoImpl() {
		super(Question.class);
	}
	
	static final String JPQL0 = "FROM Question AS q %s ORDER BY q.entered DESC";
	public Page<Question> findQuestionPage(Page<Question> page, Long projectId) {
		if(projectId!=null) {
			return findPage(page, String.format(JPQL0, "WHERE q.project.id = ?1"), projectId);
		} else {
			return findPage(page, String.format(JPQL0, ""));
		}
	}
	
}
