package CSV.TransactionAnalyzer;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateAndTimeParser implements Comparable<DateAndTimeParser> {

    private LocalDate date;

    private LocalTime time;

     public DateAndTimeParser(String inDataTime){
         String dateString = inDataTime.substring(0,10);
         String timeString = inDataTime.substring(11);
         date = parsData(dateString);
         time = parsTime(timeString);

     }
     private LocalDate parsData(String data){
         String arr[] = data.split("/");
         return LocalDate.of(Integer.parseInt(arr[2]),Integer.parseInt(arr[1]),Integer.parseInt(arr[0]));
     }
     private LocalTime parsTime(String time){
         String arr[] = time.split(":");
         return LocalTime.of(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
     }

    @Override
    public int compareTo(DateAndTimeParser o) {
      if(o.date.compareTo(date)==0){
         return o.time.compareTo(time);
      }
     return o.date.compareTo(date);

    }

}
