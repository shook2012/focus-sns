package org.osforce.connect.entity.support;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * Prefix Support Naming Strategy extends from hibernate ImprovedNamingStrategy
 * @author gavin
 * @since 1.0.0
 * @create Mar 18, 2011 - 11:10:44 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class PrefixImprovedNamingStrategy extends ImprovedNamingStrategy {
	private static final long serialVersionUID = 3097086534970692039L;
	
	private String tablePrefix = "osf_";
	private String columnPrefix = "_";
	
	public PrefixImprovedNamingStrategy() {
	}

	@Override
	public String tableName(String tableName) {
		return tablePrefix + super.tableName(tableName);
	}
	
	@Override
	public String columnName(String columnName) {
		return columnPrefix + super.columnName(columnName);
	}
	
	@Override
	public String propertyToColumnName(String propertyName) {
		return columnPrefix + super.propertyToColumnName(propertyName);
	}
	
}
