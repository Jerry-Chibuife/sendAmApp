package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.models.Package;
import africa.semicolon.sendAm.data.models.PackageDescription;
import africa.semicolon.sendAm.data.models.Status;
import africa.semicolon.sendAm.data.repositories.PackageRepository;
import africa.semicolon.sendAm.data.repositories.PackageRepositoryImplementation;
import africa.semicolon.sendAm.dtos.requests.RegisterPackageCreationForm;
import africa.semicolon.sendAm.dtos.requests.UpdateTrackingInfoRequest;
import africa.semicolon.sendAm.dtos.responses.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PackageServiceImplementation implements PackageService{

    private PackageRepository packageRepository = new PackageRepositoryImplementation();
    int counter = 0;

    @Override
    public RegisterPackageCreation createANewPackage(RegisterPackageCreationForm packageCreationForm) {
        Package aPackage = new Package();
        PackageDescription description = new PackageDescription();
        description.setName(packageCreationForm.getName());
        description.setWeightInGrammes(packageCreationForm.getWeightInGrammes());
        aPackage.setDescription(description);
        Status status = new Status();
        status.setStatus("CREATED");
        aPackage.getStatusList().add(status);
        packageRepository.save(aPackage);

        RegisterPackageCreation packageCreation = new RegisterPackageCreation();
        packageCreation.setPackageId(aPackage.getId());
        packageCreation.setTrackingId(aPackage.getTrackingId());
        packageCreation.setName(aPackage.getDescription().getName());
        packageCreation.setStatus(aPackage.getStatusList().toString());
        return packageCreation;
    }

    @Override
    public PackageRepository getRepository() {
        return packageRepository;
    }

    @Override
    public FindPackageResponse findPackageById(int id) {
        Package aPackage = packageRepository.findById(id);
        FindPackageResponse packageResponse = new FindPackageResponse();
        packageResponse.setTrackingId(aPackage.getTrackingId());
        packageResponse.setId(aPackage.getId());
        packageResponse.setName(aPackage.getDescription().getName());
        packageResponse.setStatus(aPackage.getStatusList().toString());
        return packageResponse;
    }

    @Override
    public void updatePackageInfo(Package aPackage) {
        for(Package each : packageRepository.findAll()){
            if(each.getId() == aPackage.getId()) {
                each.setDescription(aPackage.getDescription());
//                packageRepository.save(each);
            }
        }
    }

    @Override
    public Status getPackageStatus(Package aPackage) {
        return aPackage.getStatusList().get(counter);
    }

    @Override
    public void delete(Package aPackage) {
        if(packageRepository.findAll().contains(aPackage))
            packageRepository.delete(aPackage);
    }

    @Override
    public void delete(int id) {
        if(packageRepository.findById(id).getId() == id)
            packageRepository.delete(id);
    }

    @Override
    public UpdateTrackingInfoResponse updateTrackingInfo(UpdateTrackingInfoRequest updateTrackingInfo) {
        Package aPackage = packageRepository.findById(Character.getNumericValue(updateTrackingInfo.getTrackingNumber().charAt(updateTrackingInfo.getTrackingNumber().length()-1)));
        Status status = new Status();

        status.setStatus(updateTrackingInfo.getTrackingInfo());
        aPackage.getStatusList().add(status);
        packageRepository.save(aPackage);

        UpdateTrackingInfoResponse response = new UpdateTrackingInfoResponse();
        response.setStatus(updateTrackingInfo.getTrackingInfo());
        response.setDateTime(DateTimeFormatter.ofPattern("E yyyy-MM-dd a").format(status.getDateTime()));
        response.setTrackingNumber(updateTrackingInfo.getTrackingNumber());
        return null;
    }

    @Override
    public TrackPackageResponse trackPackage(int id) {
        Package aPackage = packageRepository.findById(id);
        List<Status> statusList = aPackage.getStatusList();
        TrackPackageResponse response = new TrackPackageResponse();
        for (Status status : statusList){
            TrackingInfo info = new TrackingInfo();
            info.setInformation(status.getStatus());
            info.getDateTime(DateTimeFormatter.ofPattern("E yyyy-MM-dd a").format(status.getDateTime()));
            response.getTrackingInfo().add(info);
        }
        return response;
    }


}
