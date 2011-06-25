package org.osforce.connect.entity.stream;

import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
import org.osforce.connect.entity.commons.Comment;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.spring4me.commons.collection.CollectionUtil;
import org.osforce.spring4me.entity.IdEntity;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Nov 6, 2010 - 10:19:49 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="activities")
@Cacheable
public class Activity extends IdEntity {
	private static final long serialVersionUID = 8205271185975262412L;

	public static final String NAME = Activity.class.getSimpleName();

	public static final String FORMAT_HTML = "html";
	public static final String FORMAT_TXT = "txt";
	public static final String FORMAT_FTL = "ftl";

	private Long linkedId;
	private String entity;
	@NotBlank
	private String description;
	private String type;
	private String format = FORMAT_TXT;
	private Date entered;
	private Date modified;
	// helper
	private Long enteredId;
	private Long projectId;
	private Long parentId;
	private String[] targets; // split by comma
	private List<Comment> comments = CollectionUtil.newArrayList();
	// refer
	private User enteredBy;
	private Project project;
	private Activity parent;
	private List<Activity> children = CollectionUtil.newArrayList();

	public Activity() {
	}

	public Long getLinkedId() {
		return linkedId;
	}

	public void setLinkedId(Long linkedId) {
		this.linkedId = linkedId;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	@Column(length=500)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(nullable=false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(nullable=false)
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Column(nullable=false)
	public Date getEntered() {
		return entered;
	}

	public void setEntered(Date entered) {
		this.entered = entered;
	}
	
	@Column(nullable=false)
	public Date getModified() {
		return modified;
	}
	
	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Transient
	public Long getEnteredId() {
		if(enteredId==null && enteredBy!=null) {
			enteredId = enteredBy.getId();
		}
		return enteredId;
	}

	public void setEnteredId(Long enteredId) {
		this.enteredId = enteredId;
	}

	@Transient
	public Long getProjectId() {
		if(projectId==null && project!=null) {
			projectId = project.getId();
		}
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Transient
	public Long getParentId() {
		if(parentId==null && parent!=null) {
			parentId = parent.getId();
		}
		return parentId;
	}
	
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Transient
	public String[] getTargets() {
		return targets;
	}

	public void setTargets(String[] targets) {
		this.targets = targets;
	}

	@Transient
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="entered_by_id")
	public User getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(User enteredBy) {
		this.enteredBy = enteredBy;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="project_id")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	public Activity getParent() {
		return parent;
	}
	
	public void setParent(Activity parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy="parent")
	public List<Activity> getChildren() {
		return children;
	}
	
	public void setChildren(List<Activity> children) {
		this.children = children;
	}

}
