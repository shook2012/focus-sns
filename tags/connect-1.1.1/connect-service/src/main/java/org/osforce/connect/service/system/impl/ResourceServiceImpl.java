package org.osforce.connect.service.system.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.dao.system.ResourceDao;
import org.osforce.connect.entity.system.Resource;
import org.osforce.connect.service.system.ResourceService;
import org.osforce.spring4me.commons.xml.XMLUtil;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Element;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 11:03:30 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService, ResourceLoaderAware {

	private ResourceDao resourceDao;
	private ResourceLoader resourceLoader;

	public ResourceServiceImpl() {
	}

	@Autowired
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
	
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public Resource getResource(Long resourceId) {
		return resourceDao.get(resourceId);
	}

	public Resource getResource(String code) {
		return resourceDao.findResource(code);
	}

	public void createResource(Resource resource) {
		updateResource(resource);
	}

	public void updateResource(Resource resource) {
		if(resource.getId()==null) {
			resourceDao.save(resource);
		} else {
			resourceDao.update(resource);
		}
	}

	public void deleteResource(Long resourceId) {
		resourceDao.delete(resourceId);
	}

	public Page<Resource> getResourcePage(Page<Resource> page) {
		return resourceDao.findResourcePage(page);
	}

	public List<Resource> getResourceList() {
		return resourceDao.findResourceList();
	}

	//TODO test me
	public void syncResources() {
		org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:resources.xml");
		try {
			Element resourcesEle = XMLUtil.parseToElement(resource.getFile());
			List<Element> resourceEles = XMLUtil.getChildElements(resourcesEle, "resource");
			for(Element resourceEle : resourceEles) {
				String code = XMLUtil.getAttribute(resourceEle, "code");
				Resource r = getResource(code);
				if(r==null) {
					r = new Resource();
				}
				r.setCode(code);
				r.setName(XMLUtil.getAttribute(resourceEle, "name"));
				r.setDescription(XMLUtil.getAttribute(resourceEle, "description"));
				Boolean enabled = StringUtils.equals(XMLUtil.getAttribute(resourceEle, "enabled"), "true");
				r.setEnabled(enabled);
				updateResource(r);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
