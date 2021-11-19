package co.edu.unab.BmiCalc.repository;

import co.edu.unab.BmiCalc.dataStorage.Callback;
import co.edu.unab.BmiCalc.model.Record;

public interface RecordRepository {
    public void create(Record record, Callback callback);
    public void update(Record record, Callback callback);
    public void delete(Record record, Callback callback);
    public void findAll(Callback callback);
    public void findByEmail(String email, Callback callback);
}