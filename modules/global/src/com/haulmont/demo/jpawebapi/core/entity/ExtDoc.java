/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity;

import com.haulmont.cuba.core.entity.annotation.Extends;

import javax.persistence.*;

@Entity(name = "ref$ExtDoc")
@Table(name = "REF_EXT_DOC")
@DiscriminatorValue("200")
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
@Extends(Doc.class)
public class ExtDoc extends Doc {

    private static final long serialVersionUID = 8646159950296278636L;

    @Column(name = "EXT_NAME")
    private String extName;

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }
}
