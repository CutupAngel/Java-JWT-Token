package com.chainhaus.mtg.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

/**
 * Created by Asad Sarwar on 21/06/2020.
 */
public class Util {

    public static Timestamp getCurrentTimeStamp(){
        return new Timestamp(new Date().getTime());
    }

    public static synchronized long getRandomLong(){
        return new Random().longs(Constants.ONE_BILLION, Constants.TEN_BILLION).findFirst().getAsLong();
    }

    public static String getFileName(String image){
        try{
            String[] datum = image.split("\\?")[0].split("/");
            return getCurrentTimeStamp().getTime() + "_" + getRandomLong() + "_"  + datum[datum.length -1];
        }catch (Exception e){
            return getCurrentTimeStamp().getTime() + "_" + getRandomLong() + "_"  + "_." + Constants.DEFAULT_FORMAT;
        }
    }

    public static String getImageFormat(String filePath){
        try{
            return filePath.split("\\.")[1];
        }catch (Exception e){
            return Constants.DEFAULT_FORMAT;
        }
    }
}
