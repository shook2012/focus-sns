package org.osforce.connect.service.list.impl;

import java.util.Date;
import java.util.List;

import org.osforce.connect.dao.list.LinkDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.entity.list.Link;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.service.list.LinkService;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 22, 2011 - 2:15:57 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class LinkServiceImpl implements LinkService {

	private LinkDao linkDao;
	private ProjectDao projectDao;
	
	public LinkServiceImpl() {
	}
	
	@Autowired
	public void setLinkDao(LinkDao linkDao) {
		this.linkDao = linkDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	public Link getLink(Long linkId) {
		return linkDao.get(linkId);
	}
	
	public Link getLink(Long fromId, Long toId, String entity) {
		return linkDao.findLink(fromId, toId, entity);
	}
	
	public void createLink(Link link) {
		updateLink(link);
	}

	public void updateLink(Link link) {
		if(link.getFromId()!=null) {
			Project from = projectDao.get(link.getFromId());
			link.setFrom(from);
		}
		//
		Date now = new Date();
		if(link.getId()==null) {
			link.setEntered(now);
			linkDao.save(link);
		} else {
			linkDao.update(link);
		}
	}

	public void deleteLink(Long linkId) {
		linkDao.delete(linkId);
	}
	
	public Long countLinks(String type, Long toId, String entity) {
		return linkDao.countLinks(type, toId, entity);
	}

	public Page<Link> getLinkPage(Page<Link> page, Long fromId, Long toId,
			String entity) {
		return linkDao.findLinkPage(page, fromId, toId, entity);
	}
	
	public Page<Link> getLinkPage(Page<Link> page, Project project, String entity) {
		return linkDao.findLinkPage(page, project.getId(), null, entity);
	}
	
	public Page<Link> getLinkPage(Page<Link> page, Project project, List<String> linkTypes) {
		return linkDao.findLinkPage(page, project.getId(), linkTypes);
	}
	
}
