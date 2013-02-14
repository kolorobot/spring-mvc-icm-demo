package com.github.kolorobot.icm.support.hibernate;

import org.hibernate.dialect.MySQL5Dialect;

public class MySQLDialect extends MySQL5Dialect {
	public String getTableTypeString() {
		return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
	}
}
