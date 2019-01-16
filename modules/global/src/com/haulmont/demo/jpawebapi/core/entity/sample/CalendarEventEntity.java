/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity.sample;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.util.Date;

@MetaClass(name = "ref$CalendarEventEntity")
public class CalendarEventEntity extends BaseUuidEntity {

    private static final long serialVersionUID = -7360997760133772041L;

    @MetaProperty
    protected Date start;

    @MetaProperty
    protected Date end;

    @MetaProperty
    protected String caption;

    @MetaProperty
    protected String description;

    @MetaProperty
    protected String styleName;

    @MetaProperty
    protected Boolean allDay;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getStyleName() {
        return styleName;
    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }
}
