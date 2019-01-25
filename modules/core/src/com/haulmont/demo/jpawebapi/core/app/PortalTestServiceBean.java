/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.app;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Query;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.demo.jpawebapi.core.entity.TestEntity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service(PortalTestService.NAME)
public class PortalTestServiceBean implements PortalTestService {

    @Inject
    protected Persistence persistence;

    @Override
    public void emptyMethod() {
    }

    @Override
    public TestEntity findEntityById(UUID id) {
        TestEntity result;
        Transaction tx = persistence.createTransaction();
        try {
            EntityManager em = persistence.getEntityManager();
            result = em.find(TestEntity.class, id);
            tx.commit();
        } finally {
            tx.end();
        }
        return result;
    }

    @Override
    public List<TestEntity> finAllEntities() {
        List<TestEntity> result;
        Transaction tx = persistence.createTransaction();
        try {
            EntityManager em = persistence.getEntityManager();
            result = em.createQuery("select c from jpademo_TestEntity c", TestEntity.class).getResultList();
            tx.commit();
        } finally {
            tx.end();
        }
        return result;
    }

    @Override
    public TestEntity updateFirstName(UUID id, String newFirstName) {
        TestEntity result;
        Transaction tx = persistence.createTransaction();
        try {
            EntityManager entityManager = persistence.getEntityManager();
            Query query = entityManager.createQuery("update jpademo_TestEntity c set c.fName = :newName where c.id = :id");
            query.setParameter("newName", newFirstName);
            query.setParameter("id", id);
            query.executeUpdate();

            result = entityManager.find(TestEntity.class, id);
            tx.commit();
        } finally {
            tx.end();
        }
        return result;
    }

    @Override
    public List<TestEntity> updateFirstNames(List<TestEntity> entities, String newFirstName) {
        List<TestEntity> result = new ArrayList<>();
        Transaction tx = persistence.createTransaction();
        try {
            EntityManager entityManager = persistence.getEntityManager();
            String statement = "update jpademo_TestEntity c set c.fName = :newName where c.id in(:id1,:id2)";
            Query query1 = entityManager.createQuery(statement);
            query1.setParameter("newName", newFirstName);
            UUID id_1 = entities.get(0).getId();
            UUID id_2 = entities.get(1).getId();
            query1.setParameter("id1", id_1);
            query1.setParameter("id2", id_2);
            query1.executeUpdate();
            result.add(entityManager.find(TestEntity.class, id_1));
            result.add(entityManager.find(TestEntity.class, id_2));

            tx.commit();
        } finally {
            tx.end();
        }
        return result;
    }

}