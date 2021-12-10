package insurance.Controller;

import insurance.Domain.Client;
import insurance.Domain.Employee;
import insurance.Domain.Insurance.Insurance;
import insurance.Form.LoginForm;
import insurance.Service.Client.ClientListImpl;
import insurance.Service.Employee.EmployeeListImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SignController {
    private final ClientListImpl clientService;
    private final EmployeeListImpl employeeService;

    @PostMapping("/signUp")
    public String signUp(@RequestBody Client client){
        clientService.signUp(client);
        return "성공";
    }

    @PostMapping("/client/signIn")
    public Client clientSignInForm(@RequestBody LoginForm loginForm){
        Client client = clientService.signIn(loginForm.getId(), loginForm.getPw());
//        log.info("client  {}", client);
        return client;
    }
    @PostMapping("/admin/signIn")
    public Employee adminSignInForm(@RequestBody LoginForm loginForm){
        Employee employee = employeeService.signIn(loginForm.getId(), loginForm.getPw());
        return employee;
    }
    @PostMapping("/findId")
    public String findId(@RequestBody LoginForm loginForm){
        Client client = clientService.findId(loginForm.getName(), loginForm.getEmail());
        return client.getId();
    }
    @PostMapping("/findPw")
    public String findPw(@RequestBody LoginForm loginForm){
        Client client = clientService.findPw(loginForm.getId(), loginForm.getEmail());
        return client.getPw();
    }
    @PostMapping("/duplicatedCheckId")
    public Boolean duplicatedCheckId(@RequestBody LoginForm loginForm){
        Boolean haveId = clientService.checkId(loginForm.getId());
        return haveId;
    }
    @GetMapping("/insurance") //모두가 볼 수 있기에 사인컨트롤러로, 서비스는 클라이언트를 위한거기에 클라이언트 서비스로
    public ArrayList<Insurance> getAllInsurance(){
        return clientService.getAllInsurance();
    }

}
