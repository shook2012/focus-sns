package org.osforce.connect.entity.system;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
import org.osforce.spring4me.entity.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Nov 3, 2010 - 9:42:18 AM <a
 *         href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="roles")
@Cacheable
public class Role extends IdEntity {
	private static final long serialVersionUID = 238135003201206342L;
	
	public static final Integer LEVEL_HIGH = 10;
	public static final Integer LEVEL_MIDDLE = 50;
	public static final Integer LEVEL_LOW = 100;

	@NotBlank
	private String name;
	@NotBlank
	private String code;
	private Integer level = 0;
	private String description;
	private Boolean enabled = false;
	// helper
	private Long categoryId;
	// refer
	private ProjectCategory category;

	public Role() {
	}

	@Column(nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable=false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@Transient
	public Long getCategoryId() {
		if(categoryId==null && category!=null) {
			categoryId = category.getId();
		}
		return categoryId;
	}
	
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id")
	public ProjectCategory getCategory() {
		return category;
	}
	
	public void setCategory(ProjectCategory category) {
		this.category = category;
	}

}
