package com.haulmont.demo.jpawebapi.api;

import com.haulmont.cuba.core.sys.persistence.PostgresUUID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DataSet {

    private Set<UUID> idPool = new HashSet<>();


    public UUID createEntityId() {
        UUID result = UUID.randomUUID();
        addEntityId(result);
        return result;
    }

    public void addEntityId(UUID uuid) {
        if (uuid != null)
            idPool.add(uuid);
    }

    public UUID getExistedEntity() {
        return new ArrayList<UUID>(idPool).get(0);
    }

    public void cleanup(Connection conn) throws SQLException {
        deleteInstances(conn, "JPADEMO_TEST_ENTITY", idPool);
    }

    private void deleteInstances(Connection conn, String tableName, Set<UUID> ids) throws SQLException {
        PreparedStatement stmt;
        stmt = conn.prepareStatement("delete from " + tableName + " where id = ?");
        try {
            for (UUID uuid : ids) {
                Object param = new PostgresUUID(uuid);
                stmt.setObject(1, param);
                stmt.executeUpdate();
            }
        } finally {
            stmt.close();
        }
    }

}
