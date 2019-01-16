/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "ref$MyTaskCard")
@DiscriminatorValue("410")
public class MyTaskCard extends TaskCard {

    @Column(name = "MY_INFO")
    private String myInfo;

    public String getMyInfo() {
        return myInfo;
    }

    public void setMyInfo(String myInfo) {
        this.myInfo = myInfo;
    }
}
