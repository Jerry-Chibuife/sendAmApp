package africa.semicolon.sendAm.controllers;


import africa.semicolon.sendAm.dtos.requests.RegisterPackageCreationForm;
import africa.semicolon.sendAm.dtos.requests.UpdateTrackingInfoRequest;
import africa.semicolon.sendAm.dtos.responses.FindPackageResponse;
import africa.semicolon.sendAm.dtos.responses.RegisterPackageCreation;
import africa.semicolon.sendAm.dtos.responses.TrackPackageResponse;
import africa.semicolon.sendAm.dtos.responses.UpdateTrackingInfoResponse;
import africa.semicolon.sendAm.services.PackageService;
import africa.semicolon.sendAm.services.PackageServiceImplementation;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/package")
@RestController
public class PackageController {
    private PackageService packageService = new PackageServiceImplementation();


    @PostMapping("/create")
    public RegisterPackageCreation packageCreation(@RequestBody RegisterPackageCreationForm packageCreationForm){
        return packageService.createANewPackage(packageCreationForm);
    }

    @GetMapping("/{id}")
    public TrackPackageResponse getPackageByID(@PathVariable int id){
        return packageService.trackPackage(id);
    }

    @PatchMapping("/updateStatus")
    public UpdateTrackingInfoResponse updateTrackingInfoResponse(@RequestBody UpdateTrackingInfoRequest request){
        return packageService.updateTrackingInfo(request);
    }
}
