package org.osforce.connect.task.blog;

import java.util.Map;

import org.osforce.connect.entity.blog.Post;
import org.osforce.connect.entity.commons.Statistic;
import org.osforce.connect.service.blog.PostService;
import org.osforce.connect.service.commons.StatisticService;
import org.osforce.spring4me.task.AbstractTask;
import org.osforce.spring4me.task.annotation.Task;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 2, 2011 - 3:01:37 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Task
public class PostViewCountTask extends AbstractTask {

	private PostService postService;
	private StatisticService statisticService;
	
	public PostViewCountTask() {
	}

	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
	@Autowired
	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long postId = (Long) context.get("postId");
		Post post = postService.getPost(postId); 
		Statistic statistic = statisticService.getStatistic(Statistic.TYPE_VIEW, postId, Post.NAME);
		if(statistic==null) {
			statistic = new Statistic(Statistic.TYPE_VIEW, postId, Post.NAME);
		}
		statistic.countAdd();
		statistic.setProjectId(post.getProjectId());
		statisticService.createStatistic(statistic);
	}
}
