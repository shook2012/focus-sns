package org.osforce.connect.dao.blog;

import java.util.List;

import org.osforce.connect.entity.blog.Post;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 11:01:30 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface PostDao extends BaseDao<Post> {

	/**
	 * Find blog post page and order by entered DESC
	 * @param page
	 * @param projectId
	 * @param categoryId Note: this is BlogCategory's id
	 * @return
	 */
	Page<Post> findPostList(Page<Post> page, Long projectId, Long categoryId);

	Page<Post> findPostPage(Page<Post> page, List<String> categoryCodes);

}
