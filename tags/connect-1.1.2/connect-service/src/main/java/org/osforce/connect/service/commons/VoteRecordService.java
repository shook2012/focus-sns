package org.osforce.connect.service.commons;

import org.osforce.connect.entity.commons.VoteRecord;
import org.osforce.connect.entity.system.User;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.1
 * @create Jun 3, 2011 - 5:58:04 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface VoteRecordService {

	public VoteRecord getVoteRecord(Long voteRecordId);
	
	public void createVoteRecord(VoteRecord voteRecord);
	
	public void updateVoteRecord(VoteRecord voteRecord);

	public void deleteVoteRecord(Long voteRecordId);

	public VoteRecord getVoteRecord(Long linkedId, String entity, User user);

	public Long countVoteRecords(String code, Long linkedId, String entity);
	
}
