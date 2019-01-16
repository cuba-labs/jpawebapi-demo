/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity;

import javax.persistence.*;

@Entity(name = "ref$TaskCard")
@Table(name = "REF_TASK_CARD")
@DiscriminatorValue("400")
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // changing strategy
public class TaskCard extends Card {

    @Column(name = "DETAILS")
    protected String details;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
