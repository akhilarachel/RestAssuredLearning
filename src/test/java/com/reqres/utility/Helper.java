package com.reqres.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Helper {
    public static String getRandomValue(){
        DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date randomValue = new Date();
        return customFormat.format(randomValue);
    }

    public static String getRandomUUID(){
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString();
        return fileName;
    }
}
