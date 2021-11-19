package co.edu.unab.BmiCalc.repository;

import co.edu.unab.BmiCalc.dataStorage.Callback;
import co.edu.unab.BmiCalc.model.User;

public interface UserRepository {
    public void create(User user, Callback callback);
    public void findByEmail(String email, Callback callback);
}
