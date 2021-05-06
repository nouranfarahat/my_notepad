package com.example.notepad;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteDate {

    public static String dateFromLong(long date)
    {
        DateFormat dateFormat=new SimpleDateFormat("EEE, dd MMM yyyy 'at' hh:mm aaa",
                Locale.US);
        return dateFormat.format(new Date(date));
    }
}
