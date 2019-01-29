package com.haulmont.demo.jpawebapi.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class DataSet {

    private List<UUID> idPool = new ArrayList<>();

    public UUID createEntityId() {
        UUID result = UUID.randomUUID();
        addEntityId(result);
        return result;
    }

    public void addEntityId(UUID uuid) {
        if (uuid != null)
            idPool.add(uuid);
    }

    public List<UUID> getIdPool() {
        return idPool;
    }

    public void evictFromPool(UUID id) {
        idPool.remove(id);
    }

    public void cleanup(Connection conn) throws SQLException {
        deleteInstances(conn, "JPADEMO_DRIVER", idPool);
    }

    private void deleteInstances(Connection conn, String tableName, Collection<UUID> ids) throws SQLException {
        PreparedStatement stmt;
        stmt = conn.prepareStatement("delete from " + tableName + " where id = ?");
        try {
            for (UUID uuid : ids) {
                String param = uuid.toString();
                stmt.setString(1, param);
                stmt.executeUpdate();
            }
        } finally {
            stmt.close();
        }
    }

}
