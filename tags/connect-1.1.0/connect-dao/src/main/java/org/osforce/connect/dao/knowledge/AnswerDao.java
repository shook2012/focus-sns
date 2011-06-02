package org.osforce.connect.dao.knowledge;

import org.osforce.connect.entity.knowledge.Answer;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:29:22 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface AnswerDao extends BaseDao<Answer> {

	/**
	 * Find answer page by ...
	 * @param page
	 * @param questionId
	 * @return
	 */
	Page<Answer> findAnswerPage(Page<Answer> page, Long questionId);

}
