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
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.1
 * @create Jun 3, 2011 - 9:36:04 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="vote_records")
@Cacheable
public class VoteRecord extends IdEntity {
	private static final long serialVersionUID = 619838059137057058L;
	public static final String CODE_USEFUL = "useful";
	public static final String CODE_USELESS = "useless";
	//
	private String code;
	private Long linkedId;
	private String entity;
	private Date entered;
	// helper
	private Long userId;
	// refer
	private User user;
	
	public VoteRecord() {
	}
	
	public VoteRecord(String code, Long linkedId, String entity) {
		this.code = code;
		this.linkedId = linkedId;
		this.entity = entity;
	}

	@Column(nullable=false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
