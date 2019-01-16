/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity.elementclass;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Entity(name = "ref$CustomsCard")
@Table(name = "REF_CUSTOMS_CARD")
@DiscriminatorColumn(name = "CARD_TYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("X")
public class CustomsCard extends StandardEntity {

    private static final long serialVersionUID = 2165902563577801034L;

    @Column(name = "TITLE", nullable = false)
    protected String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BUNDLE_ID", nullable = false)
    protected CustomsBundle bundle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CustomsBundle getBundle() {
        return bundle;
    }

    public void setBundle(CustomsBundle bundle) {
        this.bundle = bundle;
    }
}