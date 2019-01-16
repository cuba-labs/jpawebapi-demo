/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity;

import com.haulmont.cuba.core.entity.annotation.Extends;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "ref$SampleProductEntity")
@Extends(SamplePlatformEntity.class)
public class SampleProductEntity extends SamplePlatformEntity {

    @Column(name = "DESCRIPTION")
    protected String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
