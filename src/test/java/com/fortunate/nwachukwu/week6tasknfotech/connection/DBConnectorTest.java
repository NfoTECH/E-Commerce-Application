package com.fortunate.nwachukwu.week6tasknfotech.connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DBConnectorTest {

    @BeforeEach
    void setUp() {
        DBConnector.connection = null;
    }

    @Test
    @DisplayName("Should return a connection when the connection is not null")
    void getConnectionWhenConnectionIsNotNull() {
        Connection connection = DBConnector.getConnection();
        assertNotNull(connection);
    }
}