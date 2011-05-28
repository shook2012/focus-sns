package org.osforce.connect.task.blog;

import java.util.Map;

import org.osforce.connect.entity.blog.Post;
import org.osforce.connect.entity.stream.Activity;
import org.osforce.connect.service.blog.PostService;
import org.osforce.connect.service.stream.ActivityService;
import org.osforce.spring4me.task.AbstractTask;
import org.osforce.spring4me.task.annotation.Task;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Mar 2, 2011 - 11:22:53 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Task
public class PostActivityStreamTask extends AbstractTask {

	private ActivityService activityService;
	private PostService postService;

	public PostActivityStreamTask() {
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long postId = (Long) context.get("postId");
		Post post = postService.getPost(postId);
		String templateName = (String) context.get("template");
		Activity activity = new Activity();
		activity.setLinkedId(postId);
		activity.setEntity(Post.NAME);
		activity.setType(Post.NAME);
		activity.setDescription(templateName);
		activity.setFormat(Activity.FORMAT_FTL);
		activity.setProjectId(post.getProjectId());
		activity.setEnteredId(post.getModifiedId());
		activityService.createActivity(activity);
	}

}
