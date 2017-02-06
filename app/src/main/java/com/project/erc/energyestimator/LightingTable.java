package com.project.erc.energyestimator;

import android.provider.BaseColumns;

public final class LightingTable {

    // To prevent someone from accidentally instantiating the class,
    // make the constructor private.
    private LightingTable(){}

    //Class wrapping the lighting table name and columns.
    public static class LightingData implements BaseColumns{
        public static final String TABLE_NAME = "lighting";
        public static final String COLUMN_BID = "bid";
        public static final String COLUMN_LIGHT_TYPE = "light_type";
        public static final String COLUMN_WATTAGE = "wattage";
        public static final String COLUMN_LIGHT_COUNT = "light_count";
        public static final String COLUMN_BALLAST_FACTOR = "ballast_factor";
        public static final String COLUMN_WATTS_EA = "watts_ea";
        public static final String COLUMN_TOTAL_KW = "total_kw";
    }

    //Query to create the lighting table.
    public static final String SQL_CREATE_LIGHTING_TABLE =
            "CREATE TABLE " + LightingData.TABLE_NAME + " (" +
                    LightingData.COLUMN_BID + " INTEGER PRIMARY KEY," +
                    LightingData.COLUMN_LIGHT_TYPE + " TEXT," +
                    LightingData.COLUMN_WATTAGE + " INTEGER" +
                    LightingData.COLUMN_LIGHT_COUNT + "INTEGER" +
                    LightingData.COLUMN_BALLAST_FACTOR + "REAL" +
                    LightingData.COLUMN_WATTS_EA + "REAL" +
                    LightingData.COLUMN_TOTAL_KW + "REAL) " ;

    //Query to delete the lighting table.
    public static final String SQL_DELETE_LIGHTING_TABLE =
            "DROP TABLE IF EXISTS " + LightingData.TABLE_NAME;


}
