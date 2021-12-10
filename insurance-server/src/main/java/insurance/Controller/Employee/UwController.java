package insurance.Controller.Employee;

import insurance.Domain.Client;
import insurance.Form.RuleForm;
import insurance.Service.Employee.EmployeeListImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/uw")
public class UwController {
    private final EmployeeListImpl employeeList;

    @PostMapping("/addUw")
    public int postSuggestion(@RequestBody RuleForm ruleForm){
        int idx = employeeList.postUw(ruleForm);
        return idx;
    }

    @GetMapping("/clientList")
    public ArrayList<Client> getClientList(){
        ArrayList<Client> client = employeeList.getUwClient();
        return client;
    }

    @PostMapping("/checkUw")
    public int checkUwClient(@RequestBody RuleForm form  ){
        int clientIdx = form.getClientIdx();
        int result = employeeList.postUwClient(clientIdx);
        return 1;
    }
}
