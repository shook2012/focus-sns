package org.osforce.connect.dao.commons;

import org.osforce.connect.entity.commons.Statistic;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 11:40:20 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface StatisticDao extends BaseDao<Statistic> {

	/**
	 * Find one statistic by ... 
	 * @param linkedId
	 * @param entity
	 * @return
	 */
	public Statistic findStatistic(Long linkedId, String entity);
	
	/**
	 * Find top entity page by statistic
	 * @param page
	 * @param category
	 * @param entity
	 * @return
	 */
	public Page<Statistic> findStatisticPage(Page<Statistic> page,
			ProjectCategory category, String entity);

	

}
