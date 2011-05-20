package org.osforce.connect.dao.blog;

import java.util.List;

import org.osforce.connect.entity.blog.BlogCategory;
import org.osforce.spring4me.dao.BaseDao;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:02:23 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface BlogCategoryDao extends BaseDao<BlogCategory> {

	/**
	 * List all blog categories by project id
	 * @param projectId
	 * @return
	 */
	List<BlogCategory> findBlogCategoryList(Long projectId);

	/**
	 * find unique blog category
	 * @param projectId
	 * @param categoryLabel
	 * @return
	 */
	BlogCategory findBlogCategory(Long projectId, String categoryLabel);

}
