package insurance.Controller.Employee;

import insurance.Domain.Accident;
import insurance.Form.AccidentForm;
import insurance.Service.Employee.EmployeeListImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/handler")
public class HandlerController {
    private final EmployeeListImpl employeeList;

    @GetMapping("/accidentList")
    public ArrayList<Accident> getAccidentList(){
        ArrayList<Accident> accident = employeeList.getAccidentList();
        return accident;
    }
    @GetMapping("/clickAccident")
    public Accident getClickAccident(@RequestParam("accidentIdx") int accidentIdx){
        Accident accident = employeeList.getAccident(accidentIdx);
        return accident;
    }
    @PostMapping("/finishAccident")
    public int FinishAccident(@RequestBody AccidentForm accidentForm){
        int result = employeeList.finishAccident(accidentForm);
        return 1;
    }

}
