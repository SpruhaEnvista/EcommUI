package com.envista.msi.api.web.rest.dto.invoicing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by KRISHNAREDDYM on 6/12/2017.
 */
@Entity
public class TotalCountDto {

    @Id
    @Column(name = "ROW_NUM")
    private Long id;

    @Column(name = "TOTAL_COUNT")
    private int totalCount;

    public TotalCountDto() {
    }

    public TotalCountDto(int totalCount) {
        this.totalCount = totalCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
