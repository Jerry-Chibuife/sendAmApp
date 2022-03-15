package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.models.Package;
import africa.semicolon.sendAm.data.models.PackageDescription;
import africa.semicolon.sendAm.data.models.Status;
import africa.semicolon.sendAm.data.repositories.PackageRepositoryImplementation;
import africa.semicolon.sendAm.dtos.requests.RegisterPackageCreationForm;
import africa.semicolon.sendAm.dtos.requests.UpdateTrackingInfoRequest;
import africa.semicolon.sendAm.dtos.responses.FindPackageResponse;
import africa.semicolon.sendAm.dtos.responses.TrackPackageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PackageServiceImplementationTest {

    private PackageService packageService;

    @BeforeEach
    void beforeEachTest() {
        packageService = new PackageServiceImplementation();
    }

    @Test
    void createANewPackage_RepositoryContainsOneElementTest() {
        RegisterPackageCreationForm packageCreationForm = new RegisterPackageCreationForm();
        packageCreationForm.setName("Book");
        packageCreationForm.setWeightInGrammes(45);
        packageService.createANewPackage(packageCreationForm);
        assertEquals(1, packageService.getRepository().count());
    }

    @Test
    void afterPackageCreation_NameReturned_FindByIDTest(){
        RegisterPackageCreationForm packageCreationForm = new RegisterPackageCreationForm();
        packageCreationForm.setName("Book");
        packageCreationForm.setWeightInGrammes(45);
        packageService.createANewPackage(packageCreationForm);
        FindPackageResponse packageCreation = packageService.findPackageById(1);
        assertEquals("Book", packageCreation.getName());
    }

    @Test
    void afterPackageCreation_UpdateValuesOfPackageTest(){
        RegisterPackageCreationForm packageCreationForm = new RegisterPackageCreationForm();
        packageCreationForm.setName("Book");
        packageCreationForm.setWeightInGrammes(45);
        packageService.createANewPackage(packageCreationForm);
        Package aPackage = packageService.getRepository().findById(1);
        PackageDescription description = new PackageDescription();
        description.setName("Blue Book");
        description.setWeightInGrammes(84);
        aPackage.setDescription(description);
        packageService.updatePackageInfo(aPackage);

        Package updatedPackage = packageService.getRepository().findById(1);
        assertEquals("Blue Book", updatedPackage.getDescription().getName());
        assertEquals(1, packageService.getRepository().count());
    }

    @Test
    void getStatusOfPackageEachTimeYouQueryItTest(){
        RegisterPackageCreationForm packageCreationForm = new RegisterPackageCreationForm();
        packageCreationForm.setName("Book");
        packageCreationForm.setWeightInGrammes(45);
        packageService.createANewPackage(packageCreationForm);
        Package aPackage = packageService.getRepository().findById(1);
        Status status = packageService.getPackageStatus(packageService.getRepository().findById(1));
        assertEquals("CREATED", status.getStatus());
        assertEquals(1, aPackage.getStatusList().size());
//        System.out.println(packageService.getPackageStatus(aPackage));
    }

    @Test
    void updateStatusOfPackage_StatusListIsIncreasedBy1EachTime(){
        RegisterPackageCreationForm packageCreationForm = new RegisterPackageCreationForm();
        packageCreationForm.setName("Book");
        packageCreationForm.setWeightInGrammes(45);
        packageService.createANewPackage(packageCreationForm);
        UpdateTrackingInfoRequest updateTrackingInfo = new UpdateTrackingInfoRequest();
        updateTrackingInfo.setTrackingInfo("Arrived at Ibadan");
        updateTrackingInfo.setTrackingNumber("NGSAATK1");
        packageService.updateTrackingInfo(updateTrackingInfo);
        Package aPackage = packageService.getRepository().findById(1);
        assertEquals(2, aPackage.getStatusList().size());
    }

    @Test
    void canDeleteAPackageFromTheRepository_ByPackageObject(){
        RegisterPackageCreationForm packageCreationForm = new RegisterPackageCreationForm();
        packageCreationForm.setName("Book");
        packageCreationForm.setWeightInGrammes(45);
        packageService.createANewPackage(packageCreationForm);
        Package aPackage = packageService.getRepository().findById(1);
        packageService.delete(aPackage);
        assertEquals(0, packageService.getRepository().count());
    }

    @Test
    void canDeleteAPackageFromTheRepository_ByPackageIDTest(){
        RegisterPackageCreationForm packageCreationForm = new RegisterPackageCreationForm();
        packageCreationForm.setName("Book");
        packageCreationForm.setWeightInGrammes(45);
        packageService.createANewPackage(packageCreationForm);
        packageService.delete(1);
        assertEquals(0, packageService.getRepository().count());
    }

    @Test
    void canTrackPackageTest(){
        RegisterPackageCreationForm packageCreationForm = new RegisterPackageCreationForm();
        packageCreationForm.setName("Book");
        packageCreationForm.setWeightInGrammes(45);
        packageService.createANewPackage(packageCreationForm);
        TrackPackageResponse trackingInfo = packageService.trackPackage(1);

    }
}