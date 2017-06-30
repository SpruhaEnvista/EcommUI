package com.envista.msi.api.dao;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.web.rest.dto.UserDetailsDto;
import com.envista.msi.api.web.rest.dto.i18n.InternationalizationDto;
import org.springframework.stereotype.Repository;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.UserProfileDto;

/**
 *
 * It is just sample Dao that showcases calling storedProcedure. Dont copy and
 * paste while creating new Dao. This class will be removed or modified once
 * concrete Dao are created.
 *
 * @author Sreenivas
 *
 */
@Repository("userDetailsDao")
public class UserDetailsDao {

    @Inject
    private PersistentContext persistentContext;

    public UserDetailsDto changePassword(String enCryptedPwd, String enCryptedNewPwd, Long userId) {
        QueryParameter queryParameter = StoredProcedureParameter.with("inParamPassword",enCryptedPwd)
                .and("inNewPassword",enCryptedNewPwd)
                .and("userId", userId==null?0:userId);
        return persistentContext.findEntityAndMapFields("UserProfileTb.changePassword", queryParameter);
    }

    public UserDetailsDto updateUserProfile(String fullname,String email,String phone, Long userId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("fullNameParam",fullname)
                .and("emailParam",email)
                .and("phoneParam", phone)
                .and("userId", userId==null?0:userId);
        return persistentContext.findEntityAndMapFields("UserProfileTb.updateUserProfile", queryParameter);

    }

    public List<InternationalizationDto> getI18nLabelsByLocale(String locale){
        return persistentContext.findEntities(InternationalizationDto.Config.StoredProcedureQueryName.I18N_LABELS_BY_LOCALE, StoredProcedureParameter.with("p_locale", locale));
    }
}
