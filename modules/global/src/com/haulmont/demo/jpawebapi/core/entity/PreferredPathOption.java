/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

public enum PreferredPathOption implements EnumClass<Integer> {
    SHORTEST(0),
    NEAREST(10),
    LONGEST(20);

    private final Integer id;

    PreferredPathOption(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public static PreferredPathOption fromId(Integer id) {
        for (PreferredPathOption at : PreferredPathOption.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}