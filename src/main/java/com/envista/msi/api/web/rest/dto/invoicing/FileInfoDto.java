package com.envista.msi.api.web.rest.dto.invoicing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by admin on 6/2/2017.
 */
@Entity
public class FileInfoDto {

    @Id
    @Column(name = "FILE_INFO_ID")
    private Long id;

    @Column(name = "BUSINESS_PARTNER_NAME")
    private String businessPartnerName;
}
