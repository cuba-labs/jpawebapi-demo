/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity.elementclass;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * todo EL
 * Test entity with {@code org.apache.openjpa.persistence.jdbc.ElementClassCriteria} annotation
 *
 */
@Entity(name = "ref$CustomsBundle")
@Table(name = "REF_CUSTOMS_BUNDLE")
public class CustomsBundle extends StandardEntity {

    private static final long serialVersionUID = 7716881344004396655L;

    @Column(name = "TITLE", nullable = false)
    protected String title;

    @OneToMany(mappedBy = "bundle", fetch = FetchType.LAZY)
    //@ElementClassCriteria
    protected Set<CustomsDoc> docs;

    @OneToMany(mappedBy = "bundle", fetch = FetchType.LAZY)
    //@ElementClassCriteria
    protected Set<CustomsContract> contracts;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<CustomsDoc> getDocs() {
        return docs;
    }

    public void setDocs(Set<CustomsDoc> docs) {
        this.docs = docs;
    }

    public Set<CustomsContract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<CustomsContract> contracts) {
        this.contracts = contracts;
    }
}