package com.cogent.utils;

import com.cogent.utils.models.Date;

import java.time.LocalDateTime;

public class DateTransform {



    public static Date transform(String date){
        LocalDateTime localTime = LocalDateTime.parse(date);
        Date createdDate = new Date(localTime.getYear(), localTime.getMonth().getValue(),localTime.getDayOfMonth(), localTime.getHour()
                  , localTime.getMinute(), localTime.getSecond());

        return createdDate;
    }
}
