package com.envista.msi.api.dao.reports;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.reports.ReportsValidationDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Ashish Kumar on 09/04/2017.
 */
@Repository("ReportsValidationDao")
public class ReportsValidationDao {
    @Inject
    private PersistentContext persistentContext;

    @Transactional
    public ReportsValidationDto verifyAccounts(Long savedschedrptid, Long userId) {
        return persistentContext.findEntity("ReportsValidation.verifyAccounts",
                StoredProcedureParameter.with("p_saved_sched_rpt_id", savedschedrptid).and("p_user_id", userId));
    }

    @Transactional
    public ReportsValidationDto verifySavedSchedShippers(Long savedschedrptid, Long userId) {
        return persistentContext.findEntity("ReportsValidation.verifySavedSchedShippers",
                StoredProcedureParameter.with("p_saved_sched_rpt_id", savedschedrptid).and("p_user_id", userId));
    }
    @Transactional
    public ReportsValidationDto verifyAssignedReport(Long userId,Long rpt_id) {
        return persistentContext.findEntity("ReportsValidation.verifyAssignedReport",
                StoredProcedureParameter.with("p_user_id", userId).and("p_rpt_id", rpt_id));
    }
    @Transactional
    public ReportsValidationDto verifySavedSchedShipperGroups(Long savedschedrptid, Long userId) {
        return persistentContext.findEntity("ReportsValidation.verifySavedSchedShipperGroups",
                StoredProcedureParameter.with("p_saved_sched_rpt_id", savedschedrptid).and("p_user_id", userId));
    }

    @Transactional
    public ReportsValidationDto verifyShipperGroups(String shipperGroupIds, Long userId) {
        return persistentContext.findEntity("ReportsValidation.verifyShipperGroups",
                StoredProcedureParameter.with("p_shipperGroupIds", shipperGroupIds).and("p_user_id", userId));
    }

    @Transactional
    public ReportsValidationDto verifyShippers(String shipperIds, Long userId) {
        return persistentContext.findEntity("ReportsValidation.verifyShippers",
                StoredProcedureParameter.with("p_shipperIds", shipperIds).and("p_user_id", userId));
    }

    @Transactional
    public ReportsValidationDto verifyAssignCarrier(String carrierIds, Long userId) {
        return persistentContext.findEntity("ReportsValidation.verifyAssignCarrier",
                StoredProcedureParameter.with("p_carrierIds", carrierIds).and("p_user_id", userId));
    }

    @Transactional
    public ReportsValidationDto varifyCarrier(String userId, Long rptId,Long savedschedrptid) {
        return persistentContext.findEntity("ReportsValidation.verifyCarrier",
                StoredProcedureParameter.with("p_userId", userId).and("p_rptId", rptId).and("p_savedschedRptId",savedschedrptid));
    }

    @Transactional
    public ReportsValidationDto verifyAssignedAccounts(String customerIds, Long userId) {
        return persistentContext.findEntity("ReportsValidation.verifyAssignedAccounts",
                StoredProcedureParameter.with("p_customerIds", customerIds).and("p_user_id", userId));
    }
}
