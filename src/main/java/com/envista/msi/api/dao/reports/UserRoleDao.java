package com.envista.msi.api.dao.reports;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.reports.UserRoleDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Ashish Kumar on 17/04/2017.
 */

@Repository("UserRoleDao")
public class UserRoleDao {
    @Inject
    private PersistentContext persistentContext;
    @Transactional
    public UserRoleDto verifyuserRole(Long userId, String userRole) {
        return persistentContext.findEntity("ReportsValidation.verifyuserRole",
                StoredProcedureParameter.with("p_user_id", userId).and("p_user_role", userRole));
    }


}
