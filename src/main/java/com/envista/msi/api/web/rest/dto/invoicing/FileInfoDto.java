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

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "WEEK_END_ID")
    private String weekEndId;

    @Column(name = "CREATE_DATE")
    private String createDate;

    @Column(name = "CREATE_USER")
    private String createUser;

    @Column(name = "FILE_TYPE_ID")
    private Long fileTypeId;

    public FileInfoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getWeekEndId() {
        return weekEndId;
    }

    public void setWeekEndId(String weekEndId) {
        this.weekEndId = weekEndId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Long getFileTypeId() { return fileTypeId; }

    public void setFileTypeId(Long fileTypeId) {  this.fileTypeId = fileTypeId; }


}
