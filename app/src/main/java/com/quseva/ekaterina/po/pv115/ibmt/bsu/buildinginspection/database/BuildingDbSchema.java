package com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection.database;

/**
 * Created by Job on 12.12.2016.
 */

public class BuildingDbSchema {
    public static final class BuildingTable{
        public static final String NAME="buildings";

        public static final class Cols{
            public static final String UUID="uuid";
            public static final String TITLE="title";
            public static final String DESCRIPTION="description";
            public static final String DATE="date";
            public static final String SOLVED="solved";

        }
    }
}
