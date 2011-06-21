package org.osforce.connect.task.blog;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.osforce.connect.entity.blog.Post;
import org.osforce.spring4me.commons.collection.CollectionUtil;
import org.osforce.spring4me.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Mar 2, 2011 - 11:20:00 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Aspect
@Component
public class BlogAspect {
	private static final String TEMPLATE_POST_UPDATE = "activity/post_update.ftl";

	private Task postViewCountTask;
	private Task postActivityStreamTask;

	public BlogAspect() {
	}

	@Autowired
	@Qualifier("postViewCountTask")
	public void setPostViewCountTask(Task postViewCountTask) {
		this.postViewCountTask = postViewCountTask;
	}

	@Autowired
	@Qualifier("postActivityStreamTask")
	public void setPostActivityStreamTask(Task postActivityStreamTask) {
		this.postActivityStreamTask = postActivityStreamTask;
	}

	@AfterReturning("execution(* org.osforce.connect.service.blog.PostService.viewPost(..))")
	public void viewBlogPost(JoinPoint jp) {
		Long postId = (Long) jp.getArgs()[0];
		Map<Object, Object> context = CollectionUtil.newHashMap();
		context.put("postId", postId);
		postViewCountTask.doAsyncTask(context);
	}

	@AfterReturning("execution(* org.osforce.connect.service.blog.PostService.createPost(..)) ||"
			+ "execution(* org.osforce.connect.service.blog.PostService.updatePost(..))")
	public void updateBlogPost(JoinPoint jp) {
		Post post = (Post) jp.getArgs()[0];
		Map<Object, Object> context = CollectionUtil.newHashMap();
		context.put("postId", post.getId());
		context.put("template", TEMPLATE_POST_UPDATE);
		postActivityStreamTask.doAsyncTask(context);
	}
}
