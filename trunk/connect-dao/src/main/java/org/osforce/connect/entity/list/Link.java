package org.osforce.connect.entity.list;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.osforce.connect.entity.system.Project;
import org.osforce.spring4me.entity.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Dec 5, 2010 - 10:38:04 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="links")
@Cacheable
public class Link extends IdEntity {
	private static final long serialVersionUID = -5877643416053375175L;
	
	public static final String TYPE_FAVORITE = "favorite";
	public static final String TYPE_FOCUS = "focus";
	
	private String title;
	private String type = TYPE_FOCUS;
	private Long toId;
	private String entity;
	private Date entered;
	private Boolean enabled = true;
	// helper
	private Long fromId;
	private Object linkedEntity;
	// refer
	private Project from;
	
	public Link() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Column(nullable=false)
	public Long getToId() {
		return toId;
	}
	
	public void setToId(Long toId) {
		this.toId = toId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Column(nullable=false)
	public String getEntity() {
		return entity;
	}
	
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	public Date getEntered() {
		return entered;
	}
	
	public void setEntered(Date entered) {
		this.entered = entered;
	}
	
	@Transient
	public Long getFromId() {
		if(fromId==null && from!=null) {
			fromId = from.getId();
		}
		return fromId;
	}

	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}
	
	@Transient
	public Object getLinkedEntity() {
		return linkedEntity;
	}
	
	public void setLinkedEntity(Object linkedEntity) {
		this.linkedEntity = linkedEntity;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="from_id")
	public Project getFrom() {
		return from;
	}

	public void setFrom(Project from) {
		this.from = from;
	}
	
}
