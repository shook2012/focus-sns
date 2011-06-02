package org.osforce.connect.service.blog;

import java.util.List;

import org.osforce.connect.entity.blog.PostCategory;
import org.osforce.connect.entity.system.Project;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:19:16 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface PostCategoryService {

	PostCategory getBlogCategory(Long categoryId);
	
	PostCategory getBlogCategory(Long projectId, String categoryLabel);
	
	void createBlogCategory(PostCategory category);
	
	void updateBlogCategory(PostCategory category);
	
	void deleteBlogCategory(Long categoryId);

	List<PostCategory> getBlogCategoryList(Project project);
}
