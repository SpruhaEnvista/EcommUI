package com.envista.msi.api.web.rest.util.audit.parcel;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;

/**
 * Created by Sujit kumar on 23/06/2017.
 */
public class BigDecimalCurrencyAdapter extends XmlAdapter<String, BigDecimal> {
    @Override
    public String marshal(BigDecimal bd) throws Exception {
        if (bd != null) {
            return bd.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
        }
        return null;
    }

    @Override
    public BigDecimal unmarshal(String s) throws Exception {
        try {
            return new BigDecimal(s).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        } catch (NumberFormatException ex) {}
        return null;
    }
}
