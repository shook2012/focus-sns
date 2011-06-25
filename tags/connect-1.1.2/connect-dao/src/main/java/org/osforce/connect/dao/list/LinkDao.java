package org.osforce.connect.dao.list;

import java.util.List;

import org.osforce.connect.entity.list.Link;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 22, 2011 - 2:11:20 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface LinkDao extends BaseDao<Link> {

	/**
	 * Find unique link
	 * @param fromId
	 * @param toId
	 * @param entity
	 * @return
	 */
	Link findLink(Long fromId, Long toId, String entity);

	/**
	 * Find links 
	 * Note: fromId and toId can not be both null
	 * @param page
	 * @param fromId Note: may be null
	 * @param toId  Note: may be null
	 * @param entity
	 * @return
	 */
	Page<Link> findLinkPage(Page<Link> page, Long fromId, Long toId, String entity);
	
	Page<Link> findLinkPage(Page<Link> page, Long fromId, List<String> linkTypes);
	
	/**
	 * Count links by type toId and entity
	 * @param type
	 * @param linkedId
	 * @param entity
	 * @return
	 */
	Long countLinks(String type, Long toId, String entity);

}
