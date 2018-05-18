package com.xuexi.v2.service;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public interface IConnectionProvider {

	Connection getConnection() throws SQLException;

	void shutdown() throws SQLException;

	void initialize() throws SQLException;
}
