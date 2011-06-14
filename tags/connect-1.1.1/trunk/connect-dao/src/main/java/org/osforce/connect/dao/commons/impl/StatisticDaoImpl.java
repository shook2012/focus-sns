package org.osforce.connect.dao.commons.impl;

import org.osforce.connect.dao.commons.StatisticDao;
import org.osforce.connect.entity.commons.Statistic;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 11:41:16 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class StatisticDaoImpl extends AbstractDao<Statistic>
	implements StatisticDao {

	public StatisticDaoImpl() {
		super(Statistic.class);
	}
	
	static final String JPQL0 = "FROM Statistic AS s WHERE s.type = ?1 AND s.linkedId = ?2 AND s.entity = ?3";
	public Statistic findStatistic(String type, Long linkedId, String entity) {
		return findOne(JPQL0, type, linkedId, entity);
	}

	static final String JPQL1 = "FROM Statistic AS s WHERE s.type = ?1 AND s.entity = ?2";
	public Page<Statistic> findStatisticPage(Page<Statistic> page, String type, String entity) {
		return findPage(page, JPQL1, type, entity);
	}

}
