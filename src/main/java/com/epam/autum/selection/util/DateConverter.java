package com.epam.autum.selection.util;

import java.sql.Date;

/**
 * Created by Tapac on 09.01.2017.
 */
public class DateConverter {

    public static Date getCurrentDate(){
        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        return sqldate;
    }

}
