package repository;

import domain.Estate;

import java.sql.Connection;

public class ReadRepository {

    private Connection connection = null;

    public ReadRepository(Connection connection) {
        this.connection = connection;
    }

}
