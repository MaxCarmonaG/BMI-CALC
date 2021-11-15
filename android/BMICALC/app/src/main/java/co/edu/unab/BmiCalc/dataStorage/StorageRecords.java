package co.edu.unab.BmiCalc.dataStorage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import co.edu.unab.BmiCalc.BmiUtils;
import co.edu.unab.BmiCalc.model.Record;

public class StorageRecords {
    BmiUtils bmiUtils = new BmiUtils();
    final private ArrayList<Record> records;

    public StorageRecords() {
        Record record;
        records = new ArrayList<>();

        for(int i = 0; i < 15; i++) {
            record = new Record();
            record.setDate(bmiUtils.dateGenerator());
            record.setWeight(random(35, 100));
            record.setHeight(random(140, 210));
            record.setBmi(bmiUtils.calcBmi(record.getWeight(), record.getHeight()));
            record.setRecommendation("Maintaining a healthy weight is important for your heart health.");
            records.add(record);
        }
    }

    private int random(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }

    private float calcBmi(int height, int weight) {
        return weight / ((height / 100) * (height / 100));
    }

    static String dateGenerator() {
        Date date = new Date();
        String pattern = "dd/MM/yyy";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public ArrayList<Record> getRecords() {
        return records;
    }
}
