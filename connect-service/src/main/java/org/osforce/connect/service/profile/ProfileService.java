package org.osforce.connect.service.profile;

import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:03:56 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ProfileService {

	Profile getProfile(Long profileId);
	
	/**
	 * view profile
	 * @param project
	 * @param user for profile view statistic
	 * @return
	 */
	Profile viewProfile(Project project, User user);
	
	void createProfile(Profile profile);
	
	void updateProfile(Profile profile);
	
	void deleteProfile(Long profileId);

	Page<Profile> getProfilePage(Page<Profile> page, Long categoryId);

	Page<Profile> getProfilePage(Page<Profile> page, User user, Long categoryId);

}
