package com.epam.pool;

import com.epam.model.periodical.Publisher;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ConnectionPoolTest {

    @Test
    public void getInstance() {
    }

    @Test
    public void getConnection() {
        Connection conn = ConnectionPool.getInstance().getConnection();
        Publisher publisher = new Publisher(1, "xxx");
        try {

            PreparedStatement pr = conn.prepareStatement("insert into "

                    + "publisher(id, name) values(?,?)");

            pr.setInt(1, publisher.getId());

            pr.setString(2, publisher.getName());

            pr.executeUpdate();
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }
}