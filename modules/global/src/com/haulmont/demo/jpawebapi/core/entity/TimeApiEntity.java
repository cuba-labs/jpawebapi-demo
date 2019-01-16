/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.*;
import java.util.Date;

@NamePattern("%s|name")
@Table(name = "REF_TIME_API_ENTITY")
@Entity(name = "ref$TimeApiEntity")
public class TimeApiEntity extends StandardEntity {
    private static final long serialVersionUID = 8812940980678102240L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "DATE_")
    protected Date date;

    @Column(name = "OFFSET_DATE_TIME")
    protected OffsetDateTime offsetDateTime;

    @Column(name = "OFFSET_TIME")
    protected OffsetTime offsetTime;

    @Column(name = "LOCAL_DATE")
    protected LocalDate localDate;

    @Column(name = "LOCAL_TIME")
    protected LocalTime localTime;

    @Column(name = "LOCAL_DATE_TIME")
    protected LocalDateTime localDateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }

    public OffsetTime getOffsetTime() {
        return offsetTime;
    }

    public void setOffsetTime(OffsetTime offsetTime) {
        this.offsetTime = offsetTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}