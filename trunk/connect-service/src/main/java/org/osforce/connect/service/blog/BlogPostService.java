package org.osforce.connect.service.blog;

import org.osforce.connect.entity.blog.BlogPost;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:18:20 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface BlogPostService {

	BlogPost viewBlogPost(Long postId);
	
	BlogPost getBlogPost(Long postId);
	
	void createBlogPost(BlogPost post);
	
	void updateBlogPost(BlogPost post);
	
	void deleteBlogPost(Long postId);
	
	/**
	 * Find blog post by page
	 * @param page
	 * @param projectId Note: project id may be null
	 * @param categoryId Note: category id may be null
	 * @return
	 */
	Page<BlogPost> getBlogPostPage(Page<BlogPost> page, Long projectId, Long categoryId);

}
