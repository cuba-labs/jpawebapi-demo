/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.app;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.demo.jpawebapi.core.entity.TestEntity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
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
            result = em.createQuery("select c from JPADEMO_TEST_ENTITY c", TestEntity.class).getResultList();
            tx.commit();
        } finally {
            tx.end();
        }
        return result;
    }


}