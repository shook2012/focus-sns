package org.osforce.connect.dao.commons;

import org.osforce.connect.entity.commons.VoteRecord;
import org.osforce.spring4me.dao.BaseDao;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.1
 * @create Jun 3, 2011 - 10:02:20 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface VoteRecordDao extends BaseDao<VoteRecord> {

	/**
	 * Find vote record by linked id and entity and userId
	 * @param linkedId
	 * @param entity
	 * @param id
	 * @return
	 */
	VoteRecord findVoteRecord(Long linkedId, String entity, Long userId);

	/**
	 * Count vote record by code , linkd id and entity
	 * @param codeUseful
	 * @param linkedId
	 * @param entity
	 * @return
	 */
	Long countVoteRecords(String code, Long linkedId, String entity);

}
