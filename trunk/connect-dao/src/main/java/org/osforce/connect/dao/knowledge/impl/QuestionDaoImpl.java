package org.osforce.connect.dao.knowledge.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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
	
	static final String JPQL0 = "FROM Question AS q %s";
	public Page<Question> findQuestionPage(Page<Question> page, Long projectId) {
		if(projectId!=null) {
			return findPage(page, String.format(JPQL0, "WHERE q.project.id = ?1"), projectId);
		} else {
			return findPage(page, String.format(JPQL0, ""));
		}
	}

	public Page<Question> findQuestionPage(Page<Question> page,
			Long projectId, String orderFactor) {
		if(StringUtils.equals("view", orderFactor)) {
			page.desc("q.views");
			return findQuestionPage(page, projectId);
		} else if (StringUtils.equals("answer", orderFactor)) {
			page.desc("q.answersSize");
			return findQuestionPage(page, projectId);
		} else {
			page.desc("q.modified");
			return findQuestionPage(page, projectId);
		}
	}

	static final String JPQL3 = "FROM Question AS q WHERE q.project.category.code IN (?1)";
	public Page<Question> findQuestionPage(Page<Question> page,
			List<String> categoryCodes) {
		return findPage(page, JPQL3, categoryCodes);
	}
	
}
