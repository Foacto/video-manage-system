package com.tuan.vtube.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel implements Serializable {
    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "create__by")
    private String createBy;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "update_by")
    private String updateBy;

    @PrePersist
    private void prePersist() {
        this.updateAt = new Date();
        this.createAt = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        this.updateAt = new Date();
    }
}
