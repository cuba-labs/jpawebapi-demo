/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity(name = "ref$Shelf")
@Table(name = "REF_SHELF")
public class Shelf extends StandardEntity {

    private static final long serialVersionUID = 3487033555553686869L;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "shelf")
    private List<SampleCard> cards;

    public List<SampleCard> getCards() {
        return cards;
    }

    public void setCards(List<SampleCard> cards) {
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
