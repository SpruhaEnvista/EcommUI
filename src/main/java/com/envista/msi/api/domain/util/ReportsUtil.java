package com.envista.msi.api.domain.util;

import org.springframework.beans.factory.annotation.Value;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;

/**
 * Created by user on 4/18/2017.
 */
public class ReportsUtil {

    public static String constructMessage(String pattern, String[] arguments) throws Exception {
        String body = null;
        try {
            body = MessageFormat.format(pattern, (Object[]) arguments);
        } catch (Exception ex) {
            throw new Exception("Error generating the message" + ex.getMessage() + "for pattern " + pattern);
        }
        return body;
    }

    public static String convertDateBasedOnFormat(Object date, String format) {
        SimpleDateFormat sdfOutput = new SimpleDateFormat(format);
        return sdfOutput.format(date);
    }

}



