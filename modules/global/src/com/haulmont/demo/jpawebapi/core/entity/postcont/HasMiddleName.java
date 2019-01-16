/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity.postcont;

import javax.annotation.PostConstruct;

public interface HasMiddleName {

    String getMiddleName();

    void setMiddleName(String middleName);

    @PostConstruct
    default void initMiddleName() {
        setMiddleName("2");
    }
}
