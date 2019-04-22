package nysa.nysa_20.service.utilitary;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class TimeUtilitaryClass {
    private TimeUtilitaryClass(){}

    @NotNull
    public static String getCurrentTimeFormatMainActivity(){
        StringBuilder sb = new StringBuilder("TODAY | ");
        LocalDate localDate = LocalDate.now();
        String year = localDate.toString().substring(0,4);
        int month = Integer.parseInt(localDate.toString().substring(5,7));
        String day = localDate.toString().substring(8,10);

        sb.append(day+" "+getCurrentMonthIntToString(month)+", "+year);

        return sb.toString();
    }

    @NotNull
    @Contract(pure = true)
    private static String getCurrentMonthIntToString(int month){
        switch(month){
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "";

        }
    }

    @NotNull
    public static String getCurrentTimeFormatSymptomEntryActivity(){
        StringBuilder sb = new StringBuilder();
        LocalDate localDate = LocalDate.now();
        String year = localDate.toString().substring(0,4);
        int month = Integer.parseInt(localDate.toString().substring(5,7));
        String day = localDate.toString().substring(8,10);
        sb.append(day+" "+getCurrentMonthIntToString(month).toUpperCase()+", "+year);
        return sb.toString();
    }

    public static String getCurrentMonthYearFormat(){
        StringBuilder sb = new StringBuilder();
        LocalDate localDate = LocalDate.now();
        String year = localDate.toString().substring(0,4);
        int month = Integer.parseInt(localDate.toString().substring(5,7));
      return getCurrentMonthIntToString(month)+" "+year;
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static String getLocalDateToStringFormatSymptomTrack(LocalDate localDate){
        StringBuilder sb = new StringBuilder();
        String year = localDate.toString().substring(0,4);
        int month = Integer.parseInt(localDate.toString().substring(5,7));
        String day = localDate.toString().substring(8,10);
        sb.append(day+" "+getCurrentMonthIntToString(month)+", "+year);
        return sb.toString();
    }


}
