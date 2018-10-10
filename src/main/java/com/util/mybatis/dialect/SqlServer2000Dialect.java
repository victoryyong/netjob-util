package com.util.mybatis.dialect;

import com.util.page.Page;
import com.util.validator.ValidatorUtil;

/**
 * User: 李小勇 Date: 13-12-23下午5:14
 */
public class SqlServer2000Dialect extends Dialect {

	@Override
	public String getLimitString(String sql, Page page) {
		StringBuilder pageSql = new StringBuilder();
		if (ValidatorUtil.isNull(page.getSort())
				|| ValidatorUtil.isNull(page.getDir())) {
			page.setDir("DESC");
			page.setSort("pk_id");
		}
		pageSql.append("SELECT TOP " + page.getPageSize() + " * FROM(");
		pageSql.append(sql);
		pageSql.append(") table_a");
		pageSql.append(" WHERE (table_a.pk_id NOT IN");
		pageSql.append(" (SELECT TOP " + page.getPageSize()
				* (page.getCurrentPage() - 1));
		pageSql.append(" table_b.pk_id" + " FROM (");
		pageSql.append(sql);
		pageSql.append(" ) table_b)");
		pageSql.append(" ) ORDER BY table_a." + page.getSort() + " "
				+ page.getDir());
		return pageSql.toString();

	}
}
