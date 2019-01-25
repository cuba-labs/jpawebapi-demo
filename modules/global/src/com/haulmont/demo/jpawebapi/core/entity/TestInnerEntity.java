package com.haulmont.demo.jpawebapi.core.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "JPADEMO_TEST_INNER_ENTITY")
@Entity(name = "jpademo_TestInnerEntity")
public class TestInnerEntity extends StandardEntity {
    @Column(name = "PARAM")
    protected String param;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "innerEntity")
    protected TestEntity testEntity;

    public TestEntity getTestEntity() {
        return testEntity;
    }

    public void setTestEntity(TestEntity testEntity) {
        this.testEntity = testEntity;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}