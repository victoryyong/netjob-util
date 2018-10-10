package com.util.mybatis.dialect;

import com.util.page.Page;
import com.util.validator.ValidatorUtil;

/**
 * User: 李小勇
 * Date: 13-11-28上午11:02
 */
public class SqlServerDialect extends Dialect {

    @Override
    public String getLimitString(String sql, Page page) {
        StringBuilder pageSql = new StringBuilder();
        int beginRow = Integer.valueOf((page.getCurrentPage() - 1)
                * page.getPageSize());
        int limit = Integer.valueOf(page.getPageSize());
        if (ValidatorUtil.isNull(page.getSort())||ValidatorUtil.isNull(page.getDir())) {
        	page.setDir("DESC");
			page.setSort("pk_id");
		}
        pageSql.append("SELECT * FROM(SELECT a.*,ROW_NUMBER() OVER (ORDER BY ");
        pageSql.append("a."+page.getSort()+" "+page.getDir());
        pageSql.append(")row_num FROM(");
        pageSql.append(sql);
        pageSql.append(")a)b WHERE row_num> " + beginRow + " and row_num <= " + (beginRow + limit));
        return pageSql.toString();
    }
}
