/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Entity(name = "ref$Line")
@Table(name = "REF_LINE")
public class Line extends StandardEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POINT1_ID")
    private Point point1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POINT2_ID")
    private Point point2;

    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }
}
