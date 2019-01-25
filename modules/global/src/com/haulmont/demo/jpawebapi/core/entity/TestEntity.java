package com.haulmont.demo.jpawebapi.core.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "JPADEMO_TEST_ENTITY")
@Entity(name = "jpademo_TestEntity")
public class TestEntity extends StandardEntity {
    @Column(name = "F_NAME")
    protected String fName;

    @Column(name = "L_NAME")
    protected String lName;

    @Column(name = "AGE")
    protected Integer age;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "")
    @JoinColumn(name = "INNER_ENTITY_ID")
    protected TestInnerEntity innerEntity;

    public TestInnerEntity getInnerEntity() {
        return innerEntity;
    }

    public void setInnerEntity(TestInnerEntity innerEntity) {
        this.innerEntity = innerEntity;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }
}