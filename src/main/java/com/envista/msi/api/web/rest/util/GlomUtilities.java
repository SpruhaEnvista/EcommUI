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

        if (bean.getAction() != null)
            switch (bean.getAction()) {

                case "CREATE":
                    ruleName = "Create a user defined column named " + bean.getColumn1();
                    break;
                case "SET":
                    ruleName = "When " + bean.getColumn3() + " then populate the column " + bean.getColumn1() + " with the value " + "" + bean.getColumn5() + "";
                    break;
                case "ALIAS":
                    ruleName = "Alias column " + bean.getColumn1() + " and rename it as " + bean.getColumn2() + " if it exists";
                    break;
                case "IDENTIFY":
                    ruleName = "Create an identity named " + bean.getColumn2() + " when a column header " + bean.getColumn1() + " exists";
                    break;
                case "EXECUTE":

                    if (StringUtils.equalsIgnoreCase("PAD VALUE", bean.getExecuteFuncName()))
                        ruleName = "Pad Value ( " + bean.getColumn1() + ", " + bean.getColumn2() + ", " + bean.getColumn3() + ", " + bean.getColumn4() + " )";
                    break;
            }

        bean.setRuleName(ruleName);

        return ruleName;
    }
}
