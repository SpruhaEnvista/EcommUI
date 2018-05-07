package com.envista.msi.rating;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;


/**
 * <p>
 *
 * @author Sreenivas
 */
public class ServiceLocator {

    private static Logger m_log = Logger.getLogger(ServiceLocator.class);

    private static BasicDataSource dataSource;

    private static EntityManagerFactory emFactory;

    private ServiceLocator() {}

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static java.sql.Connection getConnectionPooling() throws ServiceLocatorException, SQLException {
        return getDatabaseConnection();
    }

    public static Connection getDatabaseConnection() throws ServiceLocatorException, SQLException {

        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            dataSource.setUrl("jdbc:oracle:thin:@10.20.0.12:1521:DB01");
            dataSource.setUsername("ship");
            dataSource.setPassword("ship");
            dataSource.setMaxTotal(10);
            dataSource.setMaxIdle(5);
            dataSource.setMinEvictableIdleTimeMillis(60000);
            dataSource.setTimeBetweenEvictionRunsMillis(70000);
        }

        java.sql.Connection connection = null;
        connection = dataSource.getConnection();

        return connection;
    }
    public static EntityManager getEntityManager(){

        EntityManager entitymanager =null;

        try {
            if (emFactory == null) {
                emFactory = Persistence.createEntityManagerFactory("JPASample");
            }
            entitymanager = emFactory.createEntityManager();

        }catch (Exception e){
            e.printStackTrace();
        }

        return entitymanager;
    }
}