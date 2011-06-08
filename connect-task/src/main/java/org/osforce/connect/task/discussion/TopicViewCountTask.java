package org.osforce.connect.task.discussion;

import java.util.Map;

import org.osforce.connect.entity.commons.Statistic;
import org.osforce.connect.entity.discussion.Topic;
import org.osforce.connect.service.commons.StatisticService;
import org.osforce.connect.service.discussion.TopicService;
import org.osforce.spring4me.task.AbstractTask;
import org.osforce.spring4me.task.annotation.Task;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 2, 2011 - 3:15:02 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Task
public class TopicViewCountTask extends AbstractTask {

	private TopicService topicService;
	private StatisticService statisticService;
	
	public TopicViewCountTask() {
	}
	
	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	@Autowired
	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long topicId = (Long) context.get("topicId");
		Topic topic = topicService.getTopic(topicId);
		Statistic statistic = statisticService.getStatistic(Statistic.TYPE_VIEW, topicId, Topic.NAME);
		if(statistic==null) {
			statistic = new Statistic(Statistic.TYPE_VIEW, topicId, Topic.NAME);
		}
		statistic.countAdd();
		statistic.setProjectId(topic.getForum().getProject().getId());
		statisticService.createStatistic(statistic);
	}
}
