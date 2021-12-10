package insurance.Service.Employee;

import insurance.Domain.Accident;
import insurance.Domain.Client;
import insurance.Domain.Contract;
import insurance.Domain.Employee;
import insurance.Domain.Insurance.Insurance;
import insurance.Form.AccidentForm;
import insurance.Form.InsuranceForm;
import insurance.Form.RuleForm;
import insurance.Form.SuggestionForm;
import insurance.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional // data 변경하는 부분 이노테이션
public class EmployeeListImpl implements  EmployeeList {

    private final EmployeeRepository employeeRepository;
    private final RuleRepository ruleRepository;
    private final InsuranceRepository insuranceRepository;
    private final AccidentRepository accidentRepository;
    private final ContractRepository contractRepository;
    private final ClientRepository clientRepository;

    private final JavaMailSender javaMailSender;

    @Override
    public Employee signIn(String id, String pw) {
        return employeeRepository.findByLoginId(id)
                .filter(e -> e.getPw().equals(pw))
                .orElse(null);
    }
    ////////////////////////////Salesman////////////////////////////
    @Override
    public ArrayList<Client> getAllClient() {
        return employeeRepository.findAllClient();
    }
    @Override
    public int postSuggestion(SuggestionForm suggestionForm) {
        Client client = clientRepository.findClientOne(suggestionForm.getClientIdx());
        Employee employee = employeeRepository.findEmployeeOne(suggestionForm.getEmployeeIdx());
        Optional<Insurance> insurance = employeeRepository.findByType(suggestionForm.getInsuranceType());
        if(client == null){
            return 0;
        }
        if(client.isHaveSuggest()){
            return -1;
        }
        return  employeeRepository.postSuggestion(suggestionForm, client, employee, insurance);
    }
    @Override
    public int postSubscription(SuggestionForm subscription) {
        Contract contract = contractRepository.findContractOne(subscription.getContractIdx());
        Client client = clientRepository.findClientOne(subscription.getClientIdx());
        if(contract == null){
            return -3;
        }
        if(client == null){
            return 0;
        }
        if(!client.isHaveSuggest()){
            return -1;
        }
        if(client.isHaveSubscription()){
            return -2;
        }
        return employeeRepository.postSubscription(subscription, contract, client);
    }
    @Override
    public ArrayList<Contract> getContractCheckForm() {
        ArrayList<Contract> contractArrayList = (ArrayList<Contract>) contractRepository.findContractAll();
        ArrayList<Contract> contracts = new ArrayList<Contract>();
        for (int i =0; i< contractArrayList.size(); i++){
            if(contractArrayList.get(i).getCheckForm() && !contractArrayList.get(i).getCheckMoney() && !contractArrayList.get(i).getFinalContract() ){
                contracts.add(contractArrayList.get(i));
            }
        }
        return contracts;
    }
    @Override
    public int postFinalPayment(int contractIdx) {
        Contract contract = contractRepository.findContractOne(contractIdx);
        return contractRepository.postFinalPayment(contract);
    }
    @Override
    public ArrayList<Contract> getFinalContract() {
        ArrayList<Contract> contractArrayList = (ArrayList<Contract>) contractRepository.findContractAll();
        ArrayList<Contract> contracts = new ArrayList<Contract>();
        for (int i =0; i< contractArrayList.size(); i++){
            if(contractArrayList.get(i).getCheckForm() && contractArrayList.get(i).getCheckMoney() && !contractArrayList.get(i).getFinalContract()){
                contracts.add(contractArrayList.get(i));
            }
        }
        return contracts;
    }
    @Override
    public Contract sendMailData(int contractIdx) throws MessagingException, UnsupportedEncodingException {
        Contract contract = contractRepository.findContractOne(contractIdx);
        //메일 보내기
        String to = contract.getClient().getEmail();
        String from = "msw711666@naver.com";
        String subject = "신동아화재 보험회사 계약 완료 청약서입니다";
        StringBuilder body = new StringBuilder();
        body.append(contract.getSubscription());
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
        mimeMessageHelper.setFrom(from,"신동아화재 보험회사");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body.toString(), true);
        javaMailSender.send(message);
        return contractRepository.findContractOne(contractIdx);
    }
    @Override
    public int postFinalContract(int contractIdx){
        Contract contract = contractRepository.findContractOne(contractIdx);
        return contractRepository.postFinalContract(contract);
    }


    ////////////////////////////designer////////////////////////////
    @Override
    public int postInsurance(InsuranceForm insuranceForm) {
        return insuranceRepository.postInsurance(insuranceForm);
    }

    ////////////////////////////uw////////////////////////////
    @Override
    public int postUw(RuleForm ruleForm) {
        Employee employee = ruleRepository.findEmployeeOne(ruleForm.getEmployeeIdx());
        return ruleRepository.postUw(ruleForm,employee);
    }
    @Override //UW 고객 찾기
    public ArrayList<Client> getUwClient() {
        ArrayList<Contract> contractArrayList = employeeRepository.findAllContract();
        ArrayList<Client> clientArrayList = new ArrayList<Client>();
        for(int i =0; i < contractArrayList.size(); i++){
            if(contractArrayList.get(i).getSubscription() !=null && contractArrayList.get(i).getSubscription() !=null && !contractArrayList.get(i).getCheckForm()){
                clientArrayList.add(contractArrayList.get(i).getClient());
            }
        }
        return clientArrayList;
    }
    @Override
    public int postUwClient(int clientIdx) {
        ArrayList<Contract> contractArrayList = employeeRepository.findAllContract();
        Contract contract = new Contract();
        for(int i =0; i < contractArrayList.size(); i++){
            if(contractArrayList.get(i).getClient().getClientIdx() == clientIdx){
                contract = contractArrayList.get(i);
            }
        }
        return employeeRepository.postUwClient(clientIdx, contract);
    }


    ////////////////////////////manager////////////////////////////
    @Override
    public int postContractRule(RuleForm ruleForm) {
        return ruleRepository.postContractRule(ruleForm);
    }
    @Override
    public ArrayList<Contract> getExpirationContract() throws ParseException {
        ArrayList<Contract> contractArrayList = (ArrayList<Contract>) contractRepository.findContractAll();
        ArrayList<Contract> contracts = new ArrayList<Contract>();
        if(contractArrayList == null){
            return null;
        }
        for (int i =0; i< contractArrayList.size(); i++){
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String insuranceDataString = contractArrayList.get(i).getInsurance().getPeriod();
            boolean finish= false;
            if(contractArrayList.get(i).getCreated()==null){

            }else{
                Date contractDate = contractArrayList.get(i).getCreated();
                Date insuranceDate = dateFormat.parse(insuranceDataString);
                if(insuranceDate.after(contractDate)){
                    finish = true; //보험기간이 아직 남아있다.
                }
                if(!finish) {
                    contracts.add(contractArrayList.get(i));
                }
            }
        }
        return contracts;
    }
    @Override
    public int deleteContract(int contractIdx) {
        Contract contract = contractRepository.findContractOne(contractIdx);
        return contractRepository.deleteContract(contract);
    }


    ////////////////////////////handler////////////////////////////
    @Override  //handler 사고리스트 가져오기
    public ArrayList<Accident> getAccidentList() {
        ArrayList<Accident> accidentArrayList = accidentRepository.findAllAccident();
        ArrayList<Accident> accidents = new ArrayList<Accident>();
        for(int i=0; i< accidentArrayList.size(); i++){
            if(accidentArrayList.get(i).getEmployee() == null){
                accidents.add(accidentArrayList.get(i));
            }
        }
        return accidents;
    }
    @Override
    public Accident getAccident(int accidentIdx) {
        Accident accident = accidentRepository.findAccidentOne(accidentIdx);
        return accident;
    }
    @Override
    public int finishAccident(AccidentForm accidentForm) {
        Accident accident = accidentRepository.findAccidentOne(accidentForm.getAccidentIdx());
        Employee employee = accidentRepository.findEmployeeOne(accidentForm.getEmployeeIdx());
        return accidentRepository.finishAccident(accidentForm, accident, employee);
    }




}
