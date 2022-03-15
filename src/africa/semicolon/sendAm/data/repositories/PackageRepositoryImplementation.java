package africa.semicolon.sendAm.data.repositories;

import africa.semicolon.sendAm.data.models.Package;

import java.util.ArrayList;
import java.util.List;

public class PackageRepositoryImplementation implements PackageRepository{

    private List<Package> database = new ArrayList<>();
    private int id = 0;

    @Override
    public Package save(Package aPackage) {
        if(aPackage.getId() == 0) {
            int id = generateId();
            aPackage.setId(id);
//            aPackage.setTrackingId("SAATK" + id);
        }
        else {
            database.remove(findById(aPackage.getId()));
        }
        database.add(aPackage);
        return aPackage;
    }

    private int generateId() {
        return ++this.id;
//        return database.size() + 1;
    }

    @Override
    public Package findById(int id) {
        for (Package aPackage : database)
            if(aPackage.getId() == id)
                return aPackage;
        return null;
    }

    @Override
    public void delete(Package aPackage) {
        database.remove(aPackage);
    }

    @Override
    public void delete(int id) {
        Package foundPackage = findById(id);
        delete(foundPackage);
    }

    @Override
    public List<Package> findAll() {
        return database;
    }

    @Override
    public int count() {
        return database.size();
    }
}
