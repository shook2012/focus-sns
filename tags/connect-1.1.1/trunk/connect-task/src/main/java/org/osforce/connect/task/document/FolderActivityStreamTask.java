package org.osforce.connect.task.document;

import java.util.Map;

import org.osforce.connect.entity.document.Folder;
import org.osforce.connect.entity.stream.Activity;
import org.osforce.connect.service.document.FolderService;
import org.osforce.connect.service.stream.ActivityService;
import org.osforce.spring4me.task.AbstractTask;
import org.osforce.spring4me.task.annotation.Task;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Apr 27, 2011 - 3:26:42 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Task
public class FolderActivityStreamTask extends AbstractTask {

	private FolderService folderService;
	private ActivityService activityService;

	public FolderActivityStreamTask() {
	}

	@Autowired
	public void setFolderService(FolderService folderService) {
		this.folderService = folderService;
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long folderId = (Long) context.get("folderId");
		Folder folder = folderService.getFolder(folderId);
		String template = (String) context.get("template");
		Activity activity = new Activity();
		activity.setLinkedId(folderId);
		activity.setEntity(Folder.NAME);
		activity.setType(Folder.NAME);
		activity.setDescription(template);
		activity.setFormat(Activity.FORMAT_FTL);
		activity.setProjectId(folder.getProjectId());
		activity.setEnteredId(folder.getModifiedId());
		activityService.createActivity(activity);
	}

}
