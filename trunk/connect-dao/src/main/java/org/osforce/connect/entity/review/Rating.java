package org.osforce.connect.entity.review;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.spring4me.entity.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Dec 5, 2010 - 5:35:24 PM <a
 *         href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name = "rating")
@Cacheable
public class Rating extends IdEntity {
	private static final long serialVersionUID = 1934844229876756104L;
	@NotBlank
	private String comment;
	private Float rating;
	private Date entered;
	private Date modified;
	// helper
	private Long projectId;
	private Long enteredId;
	private Long modifiedId;
	// refer
	private Project project;
	private User enteredBy;
	private User modifiedBy;

	public Rating() {
	}
	
	@Column(length=500, nullable=false)
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(nullable=false)
	public Float getRating() {
		return rating;
	}
	
	public void setRating(Float rating) {
		this.rating = rating;
	}
	
	public Date getModified() {
		return modified;
	}
	
	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Column(nullable=false)
	public Date getEntered() {
		return entered;
	}

	public void setEntered(Date entered) {
		this.entered = entered;
	}
	
	@Transient
	public Long getProjectId() {
		if(projectId==null && project!=null) {
			this.projectId = project.getId();
		}
		return projectId;
	}
	
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	@Transient
	public Long getEnteredId() {
		if(enteredId==null && enteredBy!=null) {
			this.enteredId = enteredBy.getId();
		}
		return enteredId;
	}
	
	public void setEnteredId(Long enteredId) {
		this.enteredId = enteredId;
	}
	
	@Transient
	public Long getModifiedId() {
		if(modifiedId==null && modifiedBy!=null) {
			this.modifiedId = modifiedBy.getId();
		}
		return modifiedId;
	}
	
	public void setModifiedId(Long modifiedId) {
		this.modifiedId = modifiedId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="project_id")
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="entered_by_id")
	public User getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(User enteredBy) {
		this.enteredBy = enteredBy;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="modified_by_id")
	public User getModifiedBy() {
		return modifiedBy;
	}
	
	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
