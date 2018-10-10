package com.util.mybatis.dialect;

import com.util.page.Page;

/**
 * Dialect
 *
 * @author 李小勇
 */
public abstract class Dialect {

    public static enum Type {
        DB2, Derby, H2, HSQL, Informix, MSSQL, MS2000SQL, MySQL, Oracle, PostgreSQL, Sybase
    }

    public abstract String getLimitString(String sql, Page page);
}
