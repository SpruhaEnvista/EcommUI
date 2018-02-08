package com.envista.msi.api.service.invoicing;

import com.envista.msi.api.dao.invoicing.DashBoardDao;
import com.envista.msi.api.web.rest.dto.invoicing.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 5/8/2017.
 */
@Service
@Transactional
public class DashBoardService {

    @Inject
    private DashBoardDao dao;

    public List<DashBoardDto> getDashBoardInfo(String fromDate, String toDate, String actionType, String sort) {

        return dao.getDashBoardInfo(fromDate, toDate, actionType, sort);
    }

    public List<DashBoardDto> getPendingCredits(String fromDate, String toDate, String actionType) {

        return dao.getPendingCredits(fromDate, toDate, actionType);
    }

    public int closeCurrentWeekCredits(String userName, String action, Long weekEndId) {

        return dao.closeCurrentWeekCredits(userName, action, weekEndId);
    }

    public int scrubCredits(Long weekEndId, String userName) {

        return dao.scrubCredits(weekEndId, userName);
    }

    public FileInfoDto insertFileInfo(String fileName, Long weekEndId, String userName,Long fileTypeId) {

        return dao.insertFileInfo(fileName, weekEndId, userName,fileTypeId);
    }

    public WeekStatusDto getWeekStatusInfo(String fromDate, String toDate) {

        return dao.getWeekStatusInfo(fromDate, toDate);
    }

    public int getPendingCreditsCount(String fromDate, String toDate, String actionType) {

        return dao.getPendingCreditsCount(fromDate, toDate, actionType);
    }

    public List<FileDefDto> getFileDefTypesListInfo() {

        return dao.getFileDefTypesListInfo();
    }
    public FileDefDto validateFileType(long fileTypeId,String fileSignature) {

        return dao.validateFileType(fileTypeId,fileSignature);
    }

    public List<FileInfoDto> getFileInfoByWeekEndId(Long weekEndId) {

        return dao.getFileInfoByWeekEndId(weekEndId);
    }

    public List<ScrubCreditsHisDto> getScrubCreditsHistory(Long weekEndId) {

        return dao.getScrubCreditsHistory(weekEndId);
    }
    public int delete(Long fileInfoId) {

        return dao.delete(fileInfoId);
    }
}
