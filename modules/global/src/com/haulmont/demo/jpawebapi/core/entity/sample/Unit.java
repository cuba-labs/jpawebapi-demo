/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity.sample;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

public enum Unit implements EnumClass<String> {
    PCS("pcs"),
    KG("kg"),
    M("m"),
    GR("gr"),
    TN("tn"),
    CWT("cwt"),
    K("k"),
    KM("km"),
    DM("dm"),
    CM("cm"),
    MM("mm");

    private String id;

    Unit(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    public static Unit fromId(String id) {
        for (Unit u : values()) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }
}