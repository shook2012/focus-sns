package org.osforce.connect.service.knowledge;

import java.util.List;

import org.osforce.connect.entity.knowledge.Question;
import org.osforce.connect.entity.system.Project;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:50:12 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface QuestionService {

	/**
	 * Intercept by @see KnowledgeAspect
	 * @param questionId
	 * @return
	 */
	Question viewQuestion(Long questionId);
	
	Question getQuestion(Long questionId);
	
	void createQuestion(Question question);
	
	void updateQuestion(Question question);
	
	void deleteQuestion(Long questionId);

	Page<Question> getQuestionPage(Page<Question> page, Project project);

	Page<Question> getQuestionPage(Page<Question> page, Project project,
			String questionOrder);

	Page<Question> getQuestionPage(Page<Question> page, List<String> categoryCodes);
	
}
