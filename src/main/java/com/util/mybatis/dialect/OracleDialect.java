package com.util.mybatis.dialect;

import com.util.page.Page;
import com.util.validator.ValidatorUtil;

/**
 * oracle
 * 
 * @author 李小勇
 * 
 */
public class OracleDialect extends Dialect {

	@Override
	public String getLimitString(String sql, Page page) {
		StringBuilder pageSql = new StringBuilder();
		String beginrow = String.valueOf((page.getCurrentPage() - 1)
				* page.getPageSize());
		String endrow = String.valueOf(page.getCurrentPage()
				* page.getPageSize());
        if (ValidatorUtil.isNull(page.getSort()) || ValidatorUtil.isNull(page.getDir())) {
            page.setDir("DESC");
            page.setSort("pk_id");
        }
        pageSql.append("SELECT * FROM ( SELECT temp.*, rownum row_id FROM ( ");
		pageSql.append(sql);
        pageSql.append(" ORDER BY " + page.getSort() + " " + page.getDir());
		pageSql.append(" ) temp WHERE rownum <= ").append(endrow);
		pageSql.append(") WHERE row_id > ").append(beginrow);
		return pageSql.toString();
	}

}
