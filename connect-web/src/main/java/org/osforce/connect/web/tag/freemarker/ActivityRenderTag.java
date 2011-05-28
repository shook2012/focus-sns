package org.osforce.connect.web.tag.freemarker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.blog.Post;
import org.osforce.connect.entity.calendar.Event;
import org.osforce.connect.entity.discussion.Forum;
import org.osforce.connect.entity.discussion.Reply;
import org.osforce.connect.entity.discussion.Topic;
import org.osforce.connect.entity.document.File;
import org.osforce.connect.entity.document.Folder;
import org.osforce.connect.entity.gallery.Album;
import org.osforce.connect.entity.gallery.Photo;
import org.osforce.connect.entity.knowledge.Answer;
import org.osforce.connect.entity.knowledge.Question;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.stream.Activity;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.blog.PostService;
import org.osforce.connect.service.calendar.EventService;
import org.osforce.connect.service.discussion.ForumService;
import org.osforce.connect.service.discussion.ReplyService;
import org.osforce.connect.service.discussion.TopicService;
import org.osforce.connect.service.document.FileService;
import org.osforce.connect.service.document.FolderService;
import org.osforce.connect.service.gallery.AlbumService;
import org.osforce.connect.service.gallery.PhotoService;
import org.osforce.connect.service.knowledge.AnswerService;
import org.osforce.connect.service.knowledge.QuestionService;
import org.osforce.connect.service.profile.ProfileService;
import org.osforce.connect.service.team.MemberService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 20, 2011 - 2:55:56 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class ActivityRenderTag implements TemplateDirectiveModel, ApplicationContextAware {

	private ApplicationContext appContext;
	
	public ActivityRenderTag() {
	}
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.appContext = applicationContext;
	}
	
	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		BeanModel activityModel =  (BeanModel) params.get("activity");
		Activity activity = activityModel!=null ? (Activity) activityModel.getWrappedObject() : null;
		Assert.notNull(activity, "Render target activity can not be null!");
		if(Activity.FORMAT_FTL.equals(activity.getFormat())) {
			Object requestModel = env.getDataModel().get("Request");
			String base = ((HttpRequestHashModel) requestModel).getRequest().getContextPath();
			Template template = env.getConfiguration().getTemplate(activity.getDescription());
			Map<Object, Object> model = createModel(activity);
			model.put("base", base);
			String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			env.getOut().write(result);
		} else if(Activity.FORMAT_TXT.equals(activity.getFormat())) {
			String imageBase = params.get("imageBase").toString();
			Assert.notNull(imageBase, "Parameter imageBase can not be null when format is txt...");
			String text = parseFace(imageBase, activity.getDescription());
			text = parseImage(text);
			text = parseLink(text);
			text = parseSpecialChar(text);
			env.getOut().write(text);
		} else if(Activity.FORMAT_HTML.equals(activity.getFormat())) {
			env.getOut().write(activity.getDescription());
		} else {
			env.getOut().write(activity.getDescription());
		}
	}
	
	private Map<Object, Object> createModel(Activity activity) {
		Map<Object, Object> model = new HashMap<Object, Object>();
		if(StringUtils.equals(Profile.NAME, activity.getEntity())) {
			ProfileService profileService = appContext.getBean(ProfileService.class);
			Profile profile = profileService.getProfile(activity.getLinkedId());
			model.put("profile", profile);
		} else if(StringUtils.equals(Post.NAME, activity.getEntity())) {
			PostService postService = appContext.getBean(PostService.class);
			Post blogPost = postService.getPost(activity.getLinkedId());
			model.put("post", blogPost);
		} else if(StringUtils.equals(Forum.NAME, activity.getEntity())){
			ForumService forumService = appContext.getBean(ForumService.class);
			Forum forum = forumService.getForum(activity.getLinkedId());
			model.put("forum", forum);
		} else if(StringUtils.equals(Topic.NAME, activity.getEntity())) {
			TopicService topicService = appContext.getBean(TopicService.class);
			Topic topic = topicService.getTopic(activity.getLinkedId());
			model.put("topic", topic);
		} else if(StringUtils.equals(Reply.NAME, activity.getEntity())) {
			ReplyService replyService = appContext.getBean(ReplyService.class);
			Reply reply = replyService.getReply(activity.getLinkedId());
			model.put("reply", reply);
		} else if(StringUtils.equals(TeamMember.NAME, activity.getEntity())) {
			MemberService memberService = appContext.getBean(MemberService.class);
			TeamMember teamMember = memberService.getMember(activity.getLinkedId());
			model.put("teamMember", teamMember);
		} else if(StringUtils.equals(Event.NAME, activity.getEntity())) {
			EventService eventService = appContext.getBean(EventService.class);
			Event event = eventService.getEvent(activity.getLinkedId());
			model.put("event", event);
		} else if(StringUtils.equals(Album.NAME, activity.getEntity())) {
			AlbumService albumService = appContext.getBean(AlbumService.class);
			Album album = albumService.getAlbum(activity.getLinkedId());
			model.put("album", album);
		} else if(StringUtils.equals(Photo.NAME, activity.getEntity())) {
			PhotoService photoService = appContext.getBean(PhotoService.class);
			Photo photo = photoService.getPhoto(activity.getLinkedId());
			model.put("photo", photo);
		} else if(StringUtils.equals(Question.NAME, activity.getEntity())) {
			QuestionService questionService = appContext.getBean(QuestionService.class);
			Question question = questionService.getQuestion(activity.getLinkedId());
			model.put("question", question);
		} else if(StringUtils.equals(Answer.NAME, activity.getEntity())) {
			AnswerService answerService = appContext.getBean(AnswerService.class);
			Answer answer = answerService.getAnswer(activity.getLinkedId());
			model.put("answer", answer);
		} else if(StringUtils.equals(File.NAME, activity.getEntity())) {
			FileService fileItemService = appContext.getBean(FileService.class);
			File fileItem = fileItemService.getFile(activity.getLinkedId());
			model.put("post", fileItem);
		} else if(StringUtils.equals(Folder.NAME, activity.getEntity())) {
			FolderService folderService = appContext.getBean(FolderService.class);
			Folder folder = folderService.getFolder(activity.getLinkedId());
			model.put("folder", folder);
		}
		//
		model.put("activity", activity);
		//
		return model;
	}
	
	static final Pattern facePattern = Pattern.compile("\\[face:\\s*(.+?)\\s*\\]");
	public String parseFace(String imageBase, String text) {
		// faces
		String tmp = text;
		Matcher faceMatcher = facePattern.matcher(tmp);
		while(faceMatcher.find()) {
			String face = faceMatcher.group(1);
			String faceBlock = StringUtils.substring(text, faceMatcher.start(), faceMatcher.end());
			tmp = StringUtils.replaceOnce(tmp, faceBlock, "<img src=\""+imageBase+"/face-"+face+".png\"/>");
		}
		return tmp;
	}

	static final Pattern imgPattern = Pattern.compile("\\[img:\\s*(.+?)\\s*\\]");
	private String parseImage(String text) {
		String tmp = text;
		Matcher imgMatcher = imgPattern.matcher(tmp);
		while(imgMatcher.find()) {
			String imgUrl = imgMatcher.group(1);
			String imgBlock = StringUtils.substring(text, imgMatcher.start(), imgMatcher.end());
			tmp = StringUtils.replaceOnce(tmp, imgBlock, "<a href=\"#\" class=\"zoom-image\"><img class=\"thumbnail\" src=\""+ imgUrl +"\"/></a>");
		}
		return tmp;
	}

	static final Pattern linkPattern = Pattern.compile("\\[link:\\s*(.+?)\\s*\\]");
	private String parseLink(String text) {
		String tmp = text;
		Matcher linkMatcher = linkPattern.matcher(tmp);
		while(linkMatcher.find()) {
			String linkUrl = linkMatcher.group(1);
			String linkBlock = StringUtils.substring(text, linkMatcher.start(), linkMatcher.end());
			tmp = StringUtils.replaceOnce(tmp, linkBlock, "<a href=\""+ linkUrl +"\" target=\"_blank\">"+linkUrl+"</a>");
		}
		return tmp;
	}

	private String parseSpecialChar(String text) {
		text = StringUtils.replace(text, "\n", "<br/>");
		return text;
	}
}
