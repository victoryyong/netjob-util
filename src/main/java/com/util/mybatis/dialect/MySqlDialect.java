package com.util.mybatis.dialect;

import com.util.page.Page;
import com.util.validator.ValidatorUtil;

/**
 * mysql
 *
 * @author 李小勇
 */
public class MySqlDialect extends Dialect {

    @Override
    public String getLimitString(String sql, Page page) {
    	StringBuilder pageSql = new StringBuilder();
        String beginRow = String.valueOf((page.getCurrentPage() - 1) * 
          page.getPageSize());
        if (ValidatorUtil.isNull(page.getDir())) {
          page.setDir("desc");
        }
        if (ValidatorUtil.isNull(page.getSort())) {
          page.setSort("c_createDate");
        }
        if (sql.toUpperCase().indexOf("ORDER") > 0)
        {
          String[] sqls = null;
          if (sql.indexOf("order") > 0) {
            sqls = sql.split("order");
          } else {
            sqls = sql.split("ORDER");
          }
          sql = sqls[0];
        }
        if(ValidatorUtil.isNotNull(page.getSort())){
        	sql = sql + " ORDER BY " +page.getSort()+" is null,"+ page.getSort() + " " + page.getDir();
        }else{
        	sql = sql + " ORDER BY " + page.getSort() + " " + page.getDir();
        }
        pageSql.append(sql);
        pageSql.append(" limit ").append(beginRow).append(",").append(page.getPageSize());
        return pageSql.toString();
      }
}
