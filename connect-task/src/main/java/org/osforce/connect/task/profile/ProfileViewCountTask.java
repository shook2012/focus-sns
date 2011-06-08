package org.osforce.connect.task.profile;

import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.osforce.connect.entity.commons.Statistic;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.commons.StatisticService;
import org.osforce.connect.service.system.ProjectService;
import org.osforce.spring4me.task.AbstractTask;
import org.osforce.spring4me.task.annotation.Task;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 10:59:29 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Task
public class ProfileViewCountTask extends AbstractTask {

	private ProjectService projectService;
	private StatisticService statisticService;

	public ProfileViewCountTask() {
	}

	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long projectId = (Long) context.get("projectId");
		User user = (User) context.get("user");
		Project project = projectService.getProject(projectId);
		if(user==null || NumberUtils
				.compare(project.getEnteredBy().getId(), user.getId())!=0) {
			Statistic statistic = statisticService.getStatistic(
					Statistic.TYPE_VIEW, project.getProfile().getId(), Profile.NAME);
			if(statistic==null) {
				statistic = new Statistic(Statistic.TYPE_VIEW, project.getProfile().getId(), Profile.NAME);
			}
			statistic.countAdd();
			statistic.setProjectId(project.getId());
			statisticService.createStatistic(statistic);
		}
	}

}
