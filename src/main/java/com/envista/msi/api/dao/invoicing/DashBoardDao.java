package com.envista.msi.api.dao.invoicing;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.invoicing.*;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 5/8/2017.
 */
@Repository("dashBoardDAO")
public class DashBoardDao {

    @Inject
    private PersistentContext persistentContext;

    public List<DashBoardDto> getDashBoardInfo(String fromDate, String toDate, String actionType, String sort) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_FROM_DATE", fromDate)
                .and("P_TO_DATE", toDate).and("P_SORT_COLUMN", sort).and("P_ACTION_TYPE", actionType);

        return persistentContext.findEntities("DashBoardDto.getDashBoardInfo", queryParameter);
    }

    public List<DashBoardDto> getPendingCredits(String fromDate, String toDate, String actionType) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_FROM_DATE", fromDate)
                .and("P_TO_DATE", toDate).and("P_SORT_COLUMN", null).and("P_ACTION_TYPE", actionType);

        return persistentContext.findEntities("DashBoardDto.getPendingCreditsCount", queryParameter);

    }

    public int getPendingCreditsCount(String fromDate, String toDate, String actionType) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_FROM_DATE", fromDate)
                .and("P_TO_DATE", toDate).and("P_SORT_COLUMN", null).and("P_ACTION_TYPE", actionType);

        List<DashBoardDto> dtos = persistentContext.findEntities("DashBoardDto.getPendingCreditsCount", queryParameter);

        int count = 0;

        if (null != dtos && dtos.size() > 0) {
            count = dtos.get(0).getPendingCreditsCount();
        }

        return count;
    }

    public int closeCurrentWeekCredits(String userName, String action, Long weekEndId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_WEEK_END_ID", weekEndId).and("P_ACTION", action)
                .and("P_USER_NAME", userName);

        persistentContext.findEntities("DashBoardDto.closeCurrentWeek", queryParameter);


        return 0;
    }

    public int scrubCredits(Long weekEndId, String userName) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_WEEK_END_ID", weekEndId).and("P_USER_NAME", userName).and("P_ACTION_TYPE", null);
        persistentContext.findEntities("DashBoardDto.scrubCredits", queryParameter);

        return 0;
    }

    public FileInfoDto insertFileInfo(String fileName, Long weekEndId, String userName,Long fileTypeId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_FILE_NAME", fileName).and("P_WEEK_END_ID", weekEndId).and("P_USER_NAME", userName).and("P_FILE_TYPE_ID", fileTypeId);

        List<FileInfoDto> dtos = persistentContext.findEntities("DashBoardDto.insertFileInfo", queryParameter);
        FileInfoDto dto = null;
        if (null != dtos && dtos.size() > 0) {
            dto = dtos.get(0);
        }
        return dto;


    }

    public WeekStatusDto getWeekStatusInfo(String fromDate, String toDate) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_FROM_DATE", fromDate)
                .and("P_TO_DATE", toDate).and("P_ACTION_TYPE", null);

        List<WeekStatusDto> dtos = persistentContext.findEntities("WeekStatusDto.getWeekStatusInfo", queryParameter);
        WeekStatusDto dto = null;
        if (null != dtos && dtos.size() > 0) {
            dto = dtos.get(0);
        }
        return dto;
    }

    public List<FileDefDto> getFileDefTypesListInfo() {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_FILE_DEF_ID", 0L).and("P_FILE_SIGNATURE", null).and("P_ACTION_TYPE", "getAll");

        return persistentContext.findEntities("FileDefDto.getFileDefTypes", queryParameter);

    }

    public FileDefDto validateFileType(long fileTypeId,String fileSignature) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_FILE_DEF_ID", fileTypeId).and("P_FILE_SIGNATURE", fileSignature).and("P_ACTION_TYPE", "getFileTypeById");

        List<FileDefDto> dtos =  persistentContext.findEntities("FileDefDto.getFileDefTypes", queryParameter);
        FileDefDto dto = null;
        if (null != dtos && dtos.size() > 0) {
            dto = dtos.get(0);
        }
        return dto;

    }

    public List<FileInfoDto> getFileInfoByWeekEndId(Long weekEndId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_WEEK_END_ID", weekEndId);

        return persistentContext.findEntities("FileInfoDto.getFileInfoByWeekEndId", queryParameter);

    }

    public List<ScrubCreditsHisDto> getScrubCreditsHistory(Long weekEndId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_WEEK_END_ID", weekEndId);

        return persistentContext.findEntities("ScrubCreditsHisDto.getScrubCreditsHistory", queryParameter);

    }
    public int delete(Long fileInfoId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_FILE_INFO_ID", fileInfoId);

        List<FileDefDto> dtos = persistentContext.findEntities("FileInfoDto.delete", queryParameter);

        int count = 0;
        if (dtos != null)
            count = dtos.size();

        return count;

    }

}
