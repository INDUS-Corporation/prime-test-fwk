/*
 * PrimeTestFwk
 * Copyright 2014 and beyond, INDUS Corporation, Inc.
 * 
 * PrimeTestFwk is free software: you can redistribute it and/or modify
 * it under the terms of the LESSER GNU General Public License version 3 as 
 * published by the Free Software Foundation.
 *
 * PrimeTestFwk is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * LESSER GNU General Public License version 3 for more details.
 *
 * You should have received a copy of the LESSER GNU General Public License
 * version 3 along with PrimeTestFwk. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package com.induscorp.prime.testing.ui.core.config.database;

import java.io.File;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class DatabaseQueryHandler {
	private SessionFactory hibernateSessionFactory;

	public DatabaseQueryHandler(String hibernateCfgFile) {
		Configuration hibernateCfg = new Configuration().configure(new File(hibernateCfgFile));

		hibernateSessionFactory = hibernateCfg.buildSessionFactory();
	}

	public boolean removeTableRecord(String tableName, String whereCondition) {
		boolean removed = false;

		String query = "delete from " + tableName + " where " + whereCondition;
		removed = executeUpdateQuery(query);

		if (!removed) {
			Assert.fail("Failed to remove record using query: " + query);
		}

		return removed;
	}

	public boolean removeTableRecordNoAssert(String tableName, String whereCondition) {
		boolean removed = false;

		String query = "delete from " + tableName + " where " + whereCondition;
		removed = executeUpdateQuery(query);

		return removed;
	}

	@SuppressWarnings("rawtypes")
	public boolean checkRecordExistInTable(String tableName, String whereCondition) {
		boolean exists = false;
		String query = "select * from " + tableName + " where " + whereCondition;

		List records = executeSearchQuery(query);
		if (records == null || records.size() > 0) {
			exists = true;
		}

		return exists;
	}

	@SuppressWarnings("rawtypes")
	public void validateRecordExistInTable(String recordName, String tableName, String whereCondition) {
		boolean exists = false;
		String query = "select * from " + tableName + " where " + whereCondition;

		List records = executeSearchQuery(query);
		if (records == null || records.size() > 0) {
			exists = true;
		}

		Assert.assertTrue(exists, "'" + recordName + "' record does not exist in the database. Query: " + query);
	}

	public boolean executeUpdateQuery(String query) {
		int updatedRecords = 0;
		Session hibSession = null;
		Transaction txn = null;
		try {
			hibSession = hibernateSessionFactory.openSession();
			txn = hibSession.beginTransaction();

			SQLQuery sqlQuery = hibSession.createSQLQuery(query);
			updatedRecords = sqlQuery.executeUpdate();

			txn.commit();
			txn = null;
		} catch (Exception ex) {
			if (txn != null) {
				txn.rollback();
			}
			Reporter.log("Error in executing update query '" + query + "'. Error message: " + ex.getMessage());
			Assert.fail("Error in executing update query '" + query + "'.", ex);
		} finally {
			if (hibSession != null) {
				hibSession.close();
			}
		}

		return (updatedRecords > 0);
	}

	@SuppressWarnings("rawtypes")
	public List executeSearchQuery(String query) {
		List foundRecords = null;
		Session hibSession = null;
		try {
			Thread.sleep(5000);

			hibSession = hibernateSessionFactory.openSession();

			SQLQuery sqlQuery = hibSession.createSQLQuery(query);
			foundRecords = sqlQuery.list();

		} catch (Exception ex) {
			Reporter.log("Error in executing query '" + query + "'.");
			Assert.fail("Error in executing query '" + query + "'.", ex);
		} finally {
			if (hibSession != null) {
				hibSession.close();
			}
		}

		return foundRecords;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> executeSearchQuery(String query, Class<T> entityClass) {
		List<T> foundRecords = null;
		Session hibSession = null;
		try {
			Thread.sleep(5000);

			hibSession = hibernateSessionFactory.openSession();

			SQLQuery sqlQuery = hibSession.createSQLQuery(query);
			sqlQuery.addEntity(entityClass);
			foundRecords = sqlQuery.list();

		} catch (Exception ex) {
			Reporter.log("Error in executing query '" + query + "'.");
			Assert.fail("Error in executing query '" + query + "'.", ex);
		} finally {
			if (hibSession != null) {
				hibSession.close();
			}
		}

		return foundRecords;
	}

	@SuppressWarnings("rawtypes")
	public String getTableRowColumnValue(String tableName, String columnName, String whereCondition) {
		String columnValue = null;

		List foundRecords = null;
		Session hibSession = null;
		String query = "select " + columnName + " from " + tableName + " where " + whereCondition;
		try {
			Thread.sleep(5000);

			hibSession = hibernateSessionFactory.openSession();

			SQLQuery sqlQuery = hibSession.createSQLQuery(query);
			foundRecords = sqlQuery.list();
			if (foundRecords != null && foundRecords.size() > 0) {
				columnValue = String.valueOf(foundRecords.get(0));
			}

		} catch (Exception ex) {
			Reporter.log("Error in executing search query '" + query + "'.");
			Assert.fail("Error in executing search query '" + query + "'.", ex);
		} finally {
			if (hibSession != null) {
				hibSession.close();
			}
		}

		return columnValue;
	}

	public static void main(String[] args) {
		DatabaseQueryHandler handler = new DatabaseQueryHandler("profiles/database/hibernate.cfg.db.primedev.xml");
		// "C:\\mkrishna\\sdwis\\project\\NG_UI_TEST\\src\\main\\resources\\hibernate.cfg.testdb.xml");

		// System.out.println(handler.checkRecordExistInTable("WATER_SYSTEM",
		// "WATER_SYSTEM_ID='TX9000000'"));

		System.out.println(handler.getTableRowColumnValue("KEY_VALUE_REF", "KEY_VALUE_ID",
				"REF_CATEGORY='LE_CATEGORY' and VALUE_DATA='" + "Government Agency" + "' and PRIMACY_AGENCY_CD='HQ'"));
	}
}
