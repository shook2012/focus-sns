package org.osforce.connect.service.profile.impl;

import java.util.Date;

import org.osforce.connect.dao.commons.AttachmentDao;
import org.osforce.connect.dao.profile.ProfileDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.commons.Attachment;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.profile.ProfileService;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:05:12 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

	private UserDao userDao;
	private ProfileDao profileDao;
	private ProjectDao projectDao;
	private AttachmentDao attachmentDao;

	public ProfileServiceImpl() {
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
	}

	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Autowired
	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	public Profile getProfile(Long profileId) {
		return profileDao.get(profileId);
	}

	public Profile viewProfile(Project project, User user) {
		//return getProfile(project);
		return null;
	}

	public void createProfile(Profile profile) {
		updateProfile(profile);
	}

	public void updateProfile(Profile profile) {
		if(profile.getEnteredId()!=null) {
			User enteredBy = userDao.get(profile.getEnteredId());
			profile.setEnteredBy(enteredBy);
		}
		if(profile.getModifiedId()!=null) {
			User modifiedBy = userDao.get(profile.getModifiedId());
			profile.setModifiedBy(modifiedBy);
		}
		if(profile.getProjectId()!=null) {
			Project project = projectDao.get(profile.getProjectId());
			profile.setProject(project);
		}
		if(profile.getLogoId()!=null) {
			Attachment logo = attachmentDao.get(profile.getLogoId());
			profile.setLogo(logo);
		}
		Date now = new Date();
		profile.setModified(now);
		if(profile.getId()==null) {
			profile.setEntered(now);
			profileDao.save(profile);
		} else {
			profileDao.update(profile);
		}
	}

	public void deleteProfile(Long profileId) {
		profileDao.delete(profileId);
	}

	public Page<Profile> getProfilePage(Page<Profile> page, Long categoryId) {
		return profileDao.findProfilePage(page, categoryId);
	}
	
	public Page<Profile> getProfilePage(Page<Profile> page, User user, Long categoryId) {
		return profileDao.findProfilePage(page, user, categoryId);
	}
	
}
