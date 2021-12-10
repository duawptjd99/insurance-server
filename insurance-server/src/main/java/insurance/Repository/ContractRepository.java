package insurance.Repository;

import insurance.Domain.Contract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContractRepository {
    private final EntityManager em;

    public String getSuggestion(int clientIdx) {
        Contract contract = new Contract();
        List<Contract> contractList = findContractAll();
        for(int i=0; i < contractList.size(); i++){
            if(contractList.get(i).getClient().getClientIdx() == clientIdx){
                if (!contractList.get(i).getCheckForm()){
                    contract = contractList.get(i);
                }
            }
        }
        if(contract == null) {
            return null;
        }
        return contract.getSuggestion();
    }
    public String getSubscription(int clientIdx) {
        Contract contract = new Contract();
        List<Contract> contractList = findContractAll();
        for(int i=0; i < contractList.size(); i++){
            if(contractList.get(i).getClient().getClientIdx() == clientIdx){
                if (!contractList.get(i).getCheckForm()){
                    contract = contractList.get(i);
                }
            }
        }
        if(contract == null) {
            return null;
        }
        System.out.println(contract.getContractIdx());
        return contract.getSubscription();
    }
    public int postFinalPayment(Contract contract) {
        contract.setCheckMoney(true);
        return contract.getContractIdx();
    }
    public int postFinalContract(Contract contract) {
        Date date = new Date();
        contract.setCreated(date);
        contract.getClient().setHaveSuggest(false);
        contract.getClient().setHaveSubscription(false);
        contract.setFinalContract(true);
        return contract.getContractIdx();
    }

    public int deleteContract(Contract contract) {
        if(contract == null){
            return 0;
        }
        em.remove(contract);
        return 1;
    }



    public List<Contract> findContractAll() {
        return em.createQuery("select c from Contract c", Contract.class).getResultList();
    }
    public Contract findContractOne(int contractIdx) {
        return em.find(Contract.class, contractIdx);
    }



}
