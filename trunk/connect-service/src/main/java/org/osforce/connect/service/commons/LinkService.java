package org.osforce.connect.service.commons;

import org.osforce.connect.entity.commons.Link;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 22, 2011 - 2:14:34 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface LinkService {

	Link getLink(Long linkId);
	
	Link getLink(Long fromId, Long toId, String entity);
	
	void createLink(Link link);
	
	void updateLink(Link link);
	
	void deleteLink(Long linkId);
	
	Long countLinks(String type, Long toId, String entity);
	
	Page<Link> getLinkPage(Page<Link> page, Long fromId, Long toId, String entity);

}
