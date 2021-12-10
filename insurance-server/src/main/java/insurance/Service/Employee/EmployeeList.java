package insurance.Service.Employee;

import insurance.Domain.Accident;
import insurance.Domain.Client;
import insurance.Domain.Contract;
import insurance.Domain.Employee;
import insurance.Form.AccidentForm;
import insurance.Form.InsuranceForm;
import insurance.Form.RuleForm;
import insurance.Form.SuggestionForm;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;

public interface EmployeeList {
    //로그인
    public Employee signIn(String id, String pw);

    ////////////////////////////Salesman////////////////////////////
    public ArrayList<Client> getAllClient(); //client정보 가져오기
    public int postSuggestion(SuggestionForm suggestionForm);//제안서 등록하기
    public int postSubscription(SuggestionForm subscription);//청약서 등록하기
    public ArrayList<Contract> getContractCheckForm();
    public int postFinalPayment(int contractIdx);
    public int postFinalContract(int contractIdx) throws MessagingException, UnsupportedEncodingException;
    public ArrayList<Contract> getFinalContract();
    public Contract sendMailData(int contractIdx) throws MessagingException, UnsupportedEncodingException;

    ////////////////////////////designer////////////////////////////
    public int postInsurance(InsuranceForm insuranceForm);//보험 만들기


    ////////////////////////////uw////////////////////////////
    public int postUw(RuleForm ruleForm); //인수정책 수립
    public ArrayList<Client> getUwClient();
    public int postUwClient(int clientIdx);


    ////////////////////////////manager////////////////////////////
    public int postContractRule(RuleForm ruleForm); //계약관리 지침 작성
    public ArrayList<Contract> getExpirationContract() throws ParseException;
    public int deleteContract(int contractIdx);

    ////////////////////////////handler////////////////////////////
    public ArrayList<Accident> getAccidentList();
    public Accident getAccident(int accidentIdx);;
    public int finishAccident(AccidentForm accidentForm);



}
