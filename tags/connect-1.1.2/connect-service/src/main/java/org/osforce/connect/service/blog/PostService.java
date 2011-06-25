package org.osforce.connect.service.blog;

import java.util.List;

import org.osforce.connect.entity.blog.Post;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:18:20 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface PostService {

	Post viewPost(Long postId);
	
	Post getPost(Long postId);
	
	void createPost(Post post);
	
	void updatePost(Post post);
	
	void deletePost(Long postId);
	
	/**
	 * Find blog post by page
	 * @param page
	 * @param projectId Note: project id may be null
	 * @param categoryId Note: category id may be null
	 * @return
	 */
	Page<Post> getPostPage(Page<Post> page, Long projectId, Long categoryId);

	Page<Post> getPostPage(Page<Post> page, List<String> categoryCodes);

}
