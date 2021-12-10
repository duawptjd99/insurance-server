package insurance.Repository;

import insurance.Domain.Client;
import insurance.Domain.Contract;
import insurance.Domain.Employee;
import insurance.Domain.Insurance.Insurance;
import insurance.Form.SuggestionForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final EntityManager em;


    public Optional<Employee> findByLoginId(String loginId){
        //findAll().stream() 루프를 돌면서 찾는다
        return findAll().stream().filter(
                        e -> e.getId().equals(loginId))
                .findFirst();
    }
    private List<Employee> findAll() {
        return em.createQuery("select e from Employee e", Employee.class).getResultList();
    }
    //모든 client찾기
    public ArrayList<Client> findAllClient() {
        return (ArrayList<Client>) em.createQuery("select c from Client c", Client.class).getResultList();
    }
    //////////////////////////////////////////////////////////////////////
    public int postSuggestion(SuggestionForm suggestionForm, Client client, Employee employee, Optional<Insurance> insurance) {
        Contract contract = new Contract();
        contract.setEmployee(employee);
        contract.setSuggestion(suggestionForm.getContent());
        contract.setInsurance(insurance.get());
        contract.setClient(client);
        client.setHaveSuggest(true);
        em.persist(contract);
        return  contract.getContractIdx();
    }

    //보험찾는 로직
    private List<Insurance> findInsuranceAll() {
        return em.createQuery("select i from Insurance i", Insurance.class).getResultList();
    }
    public Optional<Insurance> findByType(String insuranceType) {
        return findInsuranceAll().stream().filter(
                        i -> i.getDecriminatorValue().equals(insuranceType))
                .findFirst();
    }

    public Employee findEmployeeOne(int idx){
        return em.find(Employee.class, idx);
    }
    //////////////////////////////////////////////////////////////////////
    public int postSubscription(SuggestionForm subscription, Contract contract, Client client) {
        //clientIdx, ContractIdx, employeeIdx, 청약서
        contract.setSubscription(subscription.getContent());
        client.setHaveSubscription(true);
        return contract.getContractIdx();
    }



    //모든 Contract
    public ArrayList<Contract> findAllContract() {
        return (ArrayList<Contract>) em.createQuery("select c from Contract c", Contract.class).getResultList();
    }

    public int postUwClient(int clientIdx, Contract contract) {
        if(contract.getContractIdx() == -1){
            return 0;
        }else {
            contract.setCheckForm(true);
            return clientIdx;
        }
    }
}
