package com.envista.msi.api.domain.util;

import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Sujit kumar on 06/02/2017.
 */
public class DashboardUtil {

    /**
     * This method is used to set fromDate and toDate to the filter based on the monthAndYear Param.
     * @param filter
     * @param monthAndYear
     * @throws Exception
     */
    public static void setDatesFromMonth(DashboardsFilterCriteria filter, String monthAndYear) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String selectedMonth = monthAndYear.split(" ")[0];
        String lastTwoDigitsOfYear = monthAndYear.split(" ")[1].replace("-", "");

        String yearOfFromDate = filter.getFromDate().split("-")[2];
        String monthOfFromDate = filter.getFromDate().split("-")[1];
        String yearOfToDate=filter.getToDate().split("-")[2];
        String monthOfToDate = filter.getToDate().split("-")[1];
        String firstTwoDigitsOfYear = yearOfFromDate.substring(0, 2);
        int year = 0;
        if (lastTwoDigitsOfYear.length() != 4) {
            year = Integer.parseInt(firstTwoDigitsOfYear + lastTwoDigitsOfYear);
        } else {
            year = Integer.parseInt(lastTwoDigitsOfYear);
        }

        Calendar c = Calendar.getInstance();
        Date date = (Date) new SimpleDateFormat("MMM", Locale.ENGLISH).parse(selectedMonth);
        c.setTime(date);
        int numericMonth = c.get(Calendar.MONTH);
        c.set(year, numericMonth, 1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String toDate = sdf.format(c.getTime());
        if (selectedMonth.equalsIgnoreCase(monthOfFromDate) && yearOfFromDate.equals(String.valueOf(year))) {
            if (!selectedMonth.equalsIgnoreCase(monthOfToDate)) {
                filter.setToDate(toDate);
            }
        } else if (selectedMonth.equalsIgnoreCase(monthOfToDate) && yearOfToDate.equals(String.valueOf(year))) {
            if (!selectedMonth.equalsIgnoreCase(monthOfFromDate)) {
                filter.setFromDate("01-" + selectedMonth + "-" + year);
            }
        } else {
            filter.setFromDate("01-" + selectedMonth + "-" + year);
            filter.setToDate(toDate);
        }
    }
}
