package insurance.Controller;

import insurance.Domain.Accident;
import insurance.Domain.Contract;
import insurance.Form.AccidentForm;
import insurance.Service.Accident.AccidentListImpl;
import insurance.Service.Contract.ContractListImpl;
import insurance.Service.SignFrame;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/client")
public class ClientController {
    private final AccidentListImpl accidentListService;
    private final ContractListImpl contractListService;

    @PostMapping("/addAccident")
    public String addAccident(@RequestBody AccidentForm accident){
        accidentListService.addAccident(accident);
        return "성공";
    }
    @GetMapping("contract")
    public ArrayList<Contract> getAllContract(@RequestParam("clientIdx")int clientIdx){
        System.out.println("12321231321");
        return contractListService.getAllContract(clientIdx);
    }
    @GetMapping("/suggestion")
    public String getSuggestion(@RequestParam("clientIdx")int clientIdx){
        String suggestion = contractListService.getSuggestion(clientIdx);;
        return suggestion;
    }
    @GetMapping("/subscription")
    public String getSubscription(@RequestParam("clientIdx")int clientIdx){
        String subscription = contractListService.getSubscription(clientIdx);
        return subscription;
    }
    @GetMapping("/accidentList")
    public ArrayList<Accident> getMyAccidentList(@RequestParam("clientIdx")int clientIdx){
        return accidentListService.getMyAccident(clientIdx);
    }
    @PostMapping("/addLawsuit")
    public int addLawsuit(@RequestBody AccidentForm accidentForm){
        return accidentListService.addLawsuit(accidentForm);
    }

    @PostMapping("/subscriptionSign")
    public String getSign(@RequestBody AccidentForm form){
        SignFrame signFrame = new SignFrame();
        signFrame.ShowFrame();

        return "asd";
    }
}
