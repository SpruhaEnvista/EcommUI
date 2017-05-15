package com.envista.msi.api.dao.invoicing;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.web.rest.dto.invoicing.WeekEndDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by admin on 5/15/2017.
 */
@Repository("weekEndDao")
public class WeekEndDao {

    @Inject
    private PersistentContext persistentContext;

    public List<WeekEndDto> getAll() {


        return persistentContext.findEntities("WeekEndDto.getAll", null);
    }
}
