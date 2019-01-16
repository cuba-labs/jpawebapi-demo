/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity.elementclass;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "ref$CustomsDoc")
@DiscriminatorValue(value = "D")
public class CustomsDoc extends CustomsCard {

    private static final long serialVersionUID = 8032209388202496367L;

    @Column(name = "DOC_CODE")
    protected Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}