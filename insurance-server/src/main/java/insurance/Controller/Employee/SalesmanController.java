package insurance.Controller.Employee;

import insurance.Domain.Client;
import insurance.Domain.Contract;
import insurance.Form.SubscriptionForm;
import insurance.Form.SuggestionForm;
import insurance.Service.Employee.EmployeeListImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/salesman")
public class SalesmanController {
    private final EmployeeListImpl employeeList;

    @GetMapping("/clientList")
    public ArrayList<Client> getClientList(){
        ArrayList<Client> client = employeeList.getAllClient();
        return client;
    }
    @GetMapping("contractCheckMoney")
    public ArrayList<Contract> getContractCheckForm(){
        return employeeList.getContractCheckForm();
    }
    //form타입은 새로 만드는게 중복되어서 사용
    @PostMapping("/finalPayment")
    public int postFinalPayment(@RequestBody SubscriptionForm form){
        int contractIdx = form.getContractIdx();
        return employeeList.postFinalPayment(contractIdx);
    }
    @GetMapping("getFinalContract")
    public ArrayList<Contract> getFinalContract(){
        return employeeList.getFinalContract();
    }

    @PostMapping("/finalContract")
    public int postFinalContract(@RequestBody SubscriptionForm form){
        int contractIdx = form.getContractIdx();
        return employeeList.postFinalContract(contractIdx);
    }
    @PostMapping("/addSuggest")
    public int postSuggestion(@RequestBody SuggestionForm suggestionForm){
        if(suggestionForm.getContent().length() > 5000000){
            return -2;
        }
        return employeeList.postSuggestion(suggestionForm);
    }
    @PostMapping("/addSubscription")
    public int postSubscription(@RequestBody SuggestionForm subscription){
        if(subscription.getContent().length() > 5000000){
            return -4;
        }
        return employeeList.postSubscription(subscription);
    }

    @PostMapping("/sendMail")
    public Contract sendMailData(@RequestBody SuggestionForm suggestionForm) throws MessagingException, UnsupportedEncodingException {
        return employeeList.sendMailData(suggestionForm.getContractIdx());
    }

}
