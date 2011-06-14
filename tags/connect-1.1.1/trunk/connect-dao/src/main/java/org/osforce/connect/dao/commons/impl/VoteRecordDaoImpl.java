package org.osforce.connect.dao.commons.impl;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.dao.commons.VoteRecordDao;
import org.osforce.connect.entity.commons.VoteRecord;
import org.osforce.spring4me.dao.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.1
 * @create Jun 3, 2011 - 10:05:02 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class VoteRecordDaoImpl extends AbstractDao<VoteRecord> 
	implements VoteRecordDao {

	public VoteRecordDaoImpl() {
		super(VoteRecord.class);
	}

	static final String JPQL0 = "FROM VoteRecord AS vr WHERE vr.linkedId = ?1 AND vr.entity = ?2 AND vr.user.id = ?3"; 
	public VoteRecord findVoteRecord(Long linkedId, String entity, Long userId) {
		return findOne(JPQL0, linkedId, entity, userId);
	}

	static final String JPQL1 = "SELECT COUNT(*) FROM VoteRecord AS vr WHERE vr.linkedId = ?1 AND vr.entity = ?2  %s";
	public Long countVoteRecords(String code, Long linkedId, String entity) {
		if(StringUtils.isBlank(code)) {
			return count(String.format(JPQL1, ""), linkedId, entity);
		} else {
			return count(String.format(JPQL1, " AND vr.code = ?3"), linkedId, entity, code);
		}
	}

}
