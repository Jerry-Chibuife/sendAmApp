package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.models.Package;
import africa.semicolon.sendAm.data.models.PackageDescription;
import africa.semicolon.sendAm.data.models.Status;
import africa.semicolon.sendAm.data.models.User;
import africa.semicolon.sendAm.data.repositories.PackageRepository;
import africa.semicolon.sendAm.dtos.requests.RegisterPackageCreationForm;
import africa.semicolon.sendAm.dtos.requests.UpdateTrackingInfoRequest;
import africa.semicolon.sendAm.dtos.responses.FindPackageResponse;
import africa.semicolon.sendAm.dtos.responses.RegisterPackageCreation;
import africa.semicolon.sendAm.dtos.responses.TrackPackageResponse;
import africa.semicolon.sendAm.dtos.responses.UpdateTrackingInfoResponse;

public interface PackageService {

//    Package addAPackage(Package newPackage);
//    Package updatePackageInfo(Package savedPackage);
//    Package trackPackageOwner(Package savedPackage);
//    Package orderAPackage(User newUser);

    RegisterPackageCreation createANewPackage(RegisterPackageCreationForm packageCreationForm);

    PackageRepository getRepository();

    FindPackageResponse findPackageById(int id);

    void updatePackageInfo(Package aPackage);

    Status getPackageStatus(Package aPackage);

    void delete(Package aPackage);

    void delete(int id);

    UpdateTrackingInfoResponse updateTrackingInfo(UpdateTrackingInfoRequest updateTrackingInfo);

    TrackPackageResponse trackPackage(int id);
}
