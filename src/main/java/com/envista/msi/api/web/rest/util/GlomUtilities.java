package com.envista.msi.api.web.rest.util;

import com.envista.msi.api.web.rest.dto.glom.DataCriteriaDto;
import com.envista.msi.api.web.rest.dto.glom.GlmGenericTypeBean;
import com.envista.msi.api.web.rest.dto.glom.RulesBean;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 12/14/2017.
 */
public class GlomUtilities {

    private static Logger LOG = LoggerFactory.getLogger(InvoicingUtilities.class);

    public static List<GlmGenericTypeBean> prepareDataCriteria(List<DataCriteriaDto> dtos) {

        List<GlmGenericTypeBean> genericTypeBeans = new ArrayList<GlmGenericTypeBean>();
        GlmGenericTypeBean bean;
        if (dtos != null)
            for (DataCriteriaDto dto : dtos) {
                bean = new GlmGenericTypeBean();
                bean.setParam1(String.valueOf(dto.getCodeValueId()));
                bean.setParam2(dto.getColumnName());
                bean.setParam3(dto.getCriOperator());
                bean.setParam4(dto.getAndOrCondition());
                bean.setParam5(dto.getCriValue());

                genericTypeBeans.add(bean);
            }

        return genericTypeBeans;
    }

    public static String prepareRuleName(RulesBean bean) {

        String ruleName = "";

        if (bean.getAction() != null) {

            if (bean.getColumn1() == null)
                bean.setColumn1("");
            if (bean.getColumn2() == null)
                bean.setColumn2("");
            if (bean.getColumn3() == null)
                bean.setColumn3("");
            if (bean.getColumn4() == null)
                bean.setColumn4("");
            if (bean.getColumn5() == null)
                bean.setColumn5("");
            if (bean.getColumn6() == null)
                bean.setColumn6("");

            switch (bean.getAction()) {

                case "CREATE":
                    ruleName = "Create a user defined column named " + bean.getColumn1().toUpperCase();
                    break;
                case "SET":
                    ruleName = "When " + bean.getColumn3().toUpperCase() + " then populate the column " + bean.getColumn1().toUpperCase() + " with the value " + "" + bean.getColumn5().toUpperCase() + "";
                    break;
                case "ALIAS":
                    ruleName = "Alias column " + bean.getColumn1().toUpperCase() + " and rename it as " + bean.getColumn2().toUpperCase() + " if it exists";
                    break;
                case "IDENTIFY":
                    ruleName = "Create an identity named " + bean.getColumn2().toUpperCase() + " when a column header " + bean.getColumn1().toUpperCase() + " exists";
                    break;
                case "EXECUTE":

                    if (StringUtils.equalsIgnoreCase("PAD VALUE", bean.getExecuteFuncName()))
                        ruleName = "Pad Value ( " + bean.getColumn1().toUpperCase() + ", " + bean.getColumn2().toUpperCase() + ", " + bean.getColumn3().toUpperCase() + ", " + bean.getColumn4().toUpperCase() + " )";
                    break;
            }
        }
        bean.setRuleName(ruleName);

        return ruleName;
    }
}
