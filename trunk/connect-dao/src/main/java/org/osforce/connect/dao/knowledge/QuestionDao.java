package org.osforce.connect.dao.knowledge;

import java.util.List;

import org.osforce.connect.entity.knowledge.Question;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:29:03 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface QuestionDao extends BaseDao<Question> {

	/**
	 * Find question page by...
	 * @param page
	 * @param projectId  Note: project id may be null
	 * @return
	 */
	Page<Question> findQuestionPage(Page<Question> page, Long projectId);

	/**
	 * Find question page by ...
	 * @param page
	 * @param project
	 * @param orderFactor
	 * @return
	 */
	Page<Question> findQuestionPage(Page<Question> page, Long projectId,
			String orderFactor);

	Page<Question> findQuestionPage(Page<Question> page,
			List<String> categoryCodes);

}
