/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */
package com.haulmont.demo.jpawebapi.core.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.util.Date;

@MetaClass(name = "ref$SampleEntity")
public class SampleEntity extends BaseUuidEntity {

    private static final long serialVersionUID = 679624284906797796L;

    @MetaProperty
    private Date timeOnly;

    public Date getTimeOnly() {
        return timeOnly;
    }

    public void setTimeOnly(Date timeOnly) {
        this.timeOnly = timeOnly;
    }
}
