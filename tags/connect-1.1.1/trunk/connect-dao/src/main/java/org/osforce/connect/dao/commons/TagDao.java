package org.osforce.connect.dao.commons;

import java.util.List;

import org.osforce.connect.entity.commons.Tag;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 9, 2011 - 9:15:47 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface TagDao extends BaseDao<Tag> {

	/**
	 * Find tag list by linkedId and entity
	 * @param linkedId
	 * @param entity
	 * @return
	 */
	List<Tag> findTagList(Long linkedId, String entity);

	/**
	 * Find tag page by starts with
	 * @param page
	 * @param startWith
	 * @return
	 */
	Page<Tag> findTagPage(Page<Tag> page, String startWith);

}
