/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.app;

import com.haulmont.demo.jpawebapi.core.entity.TestEntity;

import java.util.List;
import java.util.UUID;

/**
 * Service is used in functional tests
 */
public interface PortalTestService {

    String NAME = "demo_PortalTestService";

    void emptyMethod();

    TestEntity findEntityById(UUID id);

    List<TestEntity> finAllEntities();

}