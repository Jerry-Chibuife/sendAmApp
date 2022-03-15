package africa.semicolon.sendAm.data.repositories;

import africa.semicolon.sendAm.data.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepositoryImplementation implements UserRepository{

    private List<User> storage = new ArrayList<>();

    @Override
    public User save(User owner, String email) {
        owner.setEmail(email);
        storage.add(owner);
        return owner;
    }

    @Override
    public User findByEmail(String email) {
        for(User owner : storage)
            if(Objects.equals(owner.getEmail(), email))
                return owner;
        return null;
    }

    @Override
    public void delete(User owner) {
        storage.remove(owner);
    }

    @Override
    public void delete(String email) {
        User foundOwner = findByEmail(email);
        delete(foundOwner);
    }

    @Override
    public List<User> findAll() {
        return storage;
    }

    @Override
    public int count() {
        return storage.size();
    }
}
