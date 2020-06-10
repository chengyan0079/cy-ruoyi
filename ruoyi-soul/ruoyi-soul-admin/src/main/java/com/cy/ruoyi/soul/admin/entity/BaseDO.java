package com.cy.ruoyi.soul.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * BaseDO.
 *
 * @author xiaoyu(Myth)
 */
@Data
public class BaseDO implements Serializable {

    /**
     * primary key.
     */
    private String id;

    /**
     * created time.
     */
    private Timestamp dateCreated;

    /**
     * updated time.
     */
    private Timestamp dateUpdated;
}
