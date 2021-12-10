package insurance.Controller.Employee;

import insurance.Domain.Contract;
import insurance.Form.RuleForm;
import insurance.Form.SuggestionForm;
import insurance.Service.Employee.EmployeeListImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/manager")
public class ManagerController {
    private final EmployeeListImpl employeeList;

    @PostMapping("/contractRule")
    public int createContractGuideline(@RequestBody RuleForm ruleForm) {
        return employeeList.postContractRule(ruleForm);
    }
    @GetMapping("/expirationContract")
    public ArrayList<Contract> getExpirationContract() throws ParseException {
        return employeeList.getExpirationContract();
    }
    @PostMapping("/deleteContract")
    public int deleteContract(@RequestBody SuggestionForm form){
        int contractIdx = form.getContractIdx();
        return employeeList.deleteContract(contractIdx);
    }

}