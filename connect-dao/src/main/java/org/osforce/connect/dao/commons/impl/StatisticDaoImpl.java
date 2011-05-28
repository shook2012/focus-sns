package org.osforce.connect.dao.commons.impl;

import org.osforce.connect.dao.commons.StatisticDao;
import org.osforce.connect.entity.commons.Statistic;
import org.osforce.connect.entity.system.ProjectCategory;
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
	
	static final String JPQL0 = "FROM Statistic AS s WHERE s.linkedId = ?1 AND s.entity = ?2";
	public Statistic findStatistic(Long linkedId, String entity) {
		return findOne(JPQL0, linkedId, entity);
	}

	static final String FIND_TOP_PAGE = "SELECT s FROM Statistic s, Profile p WHERE s.linkedId = p.id AND s.project.category.id = ?1 AND s.entity = ?2 AND p.project.publish = TRUE ORDER BY s.count DESC";
	public Page<Statistic> findStatisticPage(Page<Statistic> page,
			ProjectCategory category, String entity) {
		return findPage(page, FIND_TOP_PAGE, category.getId(), entity);
	}

}
