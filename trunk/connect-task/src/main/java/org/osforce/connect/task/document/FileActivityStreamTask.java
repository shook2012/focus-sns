package org.osforce.connect.task.document;

import java.util.Map;

import org.osforce.connect.entity.document.File;
import org.osforce.connect.entity.stream.Activity;
import org.osforce.connect.service.document.FileService;
import org.osforce.connect.service.stream.ActivityService;
import org.osforce.spring4me.task.AbstractTask;
import org.osforce.spring4me.task.annotation.Task;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Apr 27, 2011 - 3:25:55 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Task
public class FileActivityStreamTask extends AbstractTask {

	private ActivityService activityService;
	private FileService fileService;

	public FileActivityStreamTask() {
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long fileItemId = (Long) context.get("filed");
		File file = fileService.getFile(fileItemId);
		String template = (String) context.get("template");
		Activity activity = new Activity();
		activity.setLinkedId(fileItemId);
		activity.setEntity(File.NAME);
		activity.setType(File.NAME);
		activity.setDescription(template);
		activity.setFormat(Activity.FORMAT_FTL);
		activity.setProjectId(file.getFolder().getProjectId());
		activity.setEnteredId(file.getModifiedId());
		activityService.createActivity(activity);
	}

}
