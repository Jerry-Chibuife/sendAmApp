package africa.semicolon.sendAm.data.repositories;

import africa.semicolon.sendAm.data.models.User;

import java.util.List;

public interface UserRepository {

    User save(User owner, String email);
    User findByEmail(String email);
    void delete(User owner);
    void delete(String email);
    List<User> findAll();
    int count();

}
