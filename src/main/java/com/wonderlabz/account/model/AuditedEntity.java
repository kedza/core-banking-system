package com.wonderlabz.account.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@ToString
public abstract class AuditedEntity implements Serializable {

    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    @Column(name = "created", updatable = false)
    @CreationTimestamp
    protected LocalDateTime created;

    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    @Column(name = "last_modified")
    @UpdateTimestamp
    protected LocalDateTime lastModified;


    @Column(name = "created_by")
    @CreatedBy
    protected String createdBy;


    @Column(name = "last_modified_by")
    @LastModifiedBy
    protected String modifiedBy;
}
