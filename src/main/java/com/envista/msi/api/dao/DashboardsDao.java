package com.envista.msi.api.dao;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.DashboardAppliedFilterDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

/**
 * Created by Sarvesh on 1/19/2017.
 */

@Repository("dashboardsDao")
public class DashboardsDao {

    @Inject
    private PersistentContext persistentContext;

   public DashboardAppliedFilterDto getUserAppliedFilter(Long userId) {
      return persistentContext.findEntity("DashAppliedFilterTb.getUserAppliedFilter",
               StoredProcedureParameter.with("p_user_id", userId));
    }

}
