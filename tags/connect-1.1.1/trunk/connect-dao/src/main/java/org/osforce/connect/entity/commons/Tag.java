package org.osforce.connect.entity.commons;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.osforce.connect.entity.system.User;
import org.osforce.spring4me.entity.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 9, 2011 - 9:12:10 AM <a
 *         href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name = "tags")
@Cacheable
public class Tag extends IdEntity {
	private static final long serialVersionUID = -2365317233401737395L;

	private String name;
	private Long linkedId;
	private String entity;
	private Date entered;
	//
	private User user;
	// helper
	private Long userId;
	private Object linkedEntity;

	public Tag() {
	}
	
	public Tag(String name, Long linkedId, String entity) {
		this.name = name;
		this.linkedId = linkedId;
		this.entity = entity;
	}

	@Column(nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable=false)
	public Long getLinkedId() {
		return linkedId;
	}

	public void setLinkedId(Long linkedId) {
		this.linkedId = linkedId;
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
	public Long getUserId() {
		if(userId==null && user!=null) {
			userId = user.getId();
		}
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Transient
	public Object getLinkedEntity() {
		return linkedEntity;
	}
	
	public void setLinkedEntity(Object linkedEntity) {
		this.linkedEntity = linkedEntity;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
