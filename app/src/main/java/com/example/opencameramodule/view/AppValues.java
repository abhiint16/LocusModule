package com.example.opencameramodule.view;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AppValues {

    public static class Constants {
        public static final String LOCUS = "locus";
        public static final String SAVED_TABLE = "savedTable";
        public static final String ID = "id";
        public static final String VALUE = "value";
        public static int REQUEST_IMAGE_CAPTURE = 1;
        public static int ITEM_POS;

        public static final String DB_NAME = "LocusDB";

        public static final int DB_VERSION = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            CardTypeValues.PHOTO,
            CardTypeValues.SINGLE_CHOICE,
            CardTypeValues.COMMENT}
    )
    public @interface CardTypeValues {

        String PHOTO = "PHOTO";
        String SINGLE_CHOICE = "SINGLE_CHOICE";
        String COMMENT = "COMMENT";
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            CardTypes.NO_TYPE,
            CardTypes.PHOTO,
            CardTypes.SINGLE_CHOICE,
            CardTypes.COMMENT}
    )
    public @interface CardTypes {

        int NO_TYPE = 0;
        int PHOTO = 1;
        int SINGLE_CHOICE = 2;
        int COMMENT = 3;
    }

}
