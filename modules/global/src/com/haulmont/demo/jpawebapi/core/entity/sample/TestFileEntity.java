/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */
package com.haulmont.demo.jpawebapi.core.entity.sample;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.FileDescriptor;

@MetaClass(name = "ref$TestFileEntity")
public class TestFileEntity extends BaseUuidEntity {

    private static final long serialVersionUID = -1129184723497144061L;

    @MetaProperty
    protected FileDescriptor file;

    public void setFile(FileDescriptor file) {
        this.file = file;
    }

    public FileDescriptor getFile() {
        return file;
    }
}