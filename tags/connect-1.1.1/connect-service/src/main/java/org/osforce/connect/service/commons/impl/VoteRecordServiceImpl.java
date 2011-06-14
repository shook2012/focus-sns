package org.osforce.connect.service.commons.impl;

import java.util.Date;

import org.osforce.connect.dao.commons.VoteRecordDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.commons.VoteRecord;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.commons.VoteRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.1
 * @create Jun 3, 2011 - 5:58:13 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class VoteRecordServiceImpl implements VoteRecordService {

	private UserDao userDao;
	private VoteRecordDao voteRecordDao;
	
	public VoteRecordServiceImpl() {
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setVoteRecordDao(VoteRecordDao voteRecordDao) {
		this.voteRecordDao = voteRecordDao;
	}
	
	public VoteRecord getVoteRecord(Long voteRecordId) {
		return voteRecordDao.get(voteRecordId);
	}
	
	public VoteRecord getVoteRecord(Long linkedId, String entity, User user) {
		return voteRecordDao.findVoteRecord(linkedId, entity, user.getId());
	}
	
	public void createVoteRecord(VoteRecord voteRecord) {
		updateVoteRecord(voteRecord);
	}

	public void updateVoteRecord(VoteRecord voteRecord) {
		if(voteRecord.getUserId()!=null) {
			User user = userDao.get(voteRecord.getUserId());
			voteRecord.setUser(user);
		}
		//
		Date now = new Date();
		if(voteRecord.getId()==null) {
			voteRecord.setEntered(now);
			voteRecordDao.save(voteRecord);
		} else {
			voteRecordDao.update(voteRecord);
		}
	}
	
	public void deleteVoteRecord(Long voteRecordId) {
		voteRecordDao.delete(voteRecordId);
	}
	
	public Long countVoteRecords(String code, Long linkedId, String entity) {
		return voteRecordDao.countVoteRecords(code, linkedId, entity);
	}

}
