package com.envista.msi.api.dao;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.TogglesDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository("TogglesDao")
public class TogglesDao {

    @Inject
    private PersistentContext persistentContext;

    public List<TogglesDto> getTogglesList() {
        return persistentContext.findEntitiesAndMapFields("TogglesDto.getTogglesDetails", null);
    }

}



