package CSV.TransactionAnalyzer;

import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

public class TransactionAnalyser {
    private String merchant;
    private DateAndTimeParser fromDate;
    private DateAndTimeParser toDate;
    private String url;
    public TransactionAnalyser(String url, String fromDate, String toDate, String merchant){
        this.url=url;
        this.fromDate = new DateAndTimeParser(fromDate);
        this.toDate = new DateAndTimeParser(toDate);
        this.merchant = merchant;

    }
    public String statistic()  {
        int counterTransactions = 0;
        double averageTransactionValue = 0;
        try(ReversedLinesFileReader reader = new ReversedLinesFileReader(
                new File("C:\\Учеба\\прога\\Hibernate\\SimpleHibernate\\src\\main\\java\\CSV\\csv_file.csv"),
                Charset.defaultCharset())) {

            Set<String> stopList = new HashSet<>();
            String nextline;
            String[] line;
            DateAndTimeParser dateAndTime;
            while ((nextline = reader.readLine()) != null) {
                line = nextline.split(",");
                if (!line[3].trim().equals(merchant)) {
                    continue;
                }
                if (line[4].trim().equals("REVERSAL")) {
                    for (int i = 5; i < line.length; i++) {
                        stopList.add(line[i].trim());
                    }
                    continue;
                }
                if (stopList.contains(line[0].trim())) {
                    stopList.remove(line[0].trim());
                    continue;
                }
                dateAndTime = new DateAndTimeParser(line[1].trim());
                if (fromDate.compareTo(dateAndTime) >= 0 && toDate.compareTo(dateAndTime) <= 0) {
                    counterTransactions++;
                    averageTransactionValue += Double.parseDouble(line[2].trim());
                }
                if(fromDate.compareTo(dateAndTime)<0){
                    break;
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return "Number of transactions = " + counterTransactions + "\n" +
                "Average Transaction Value = " + averageTransactionValue + "\n";

    }

}
