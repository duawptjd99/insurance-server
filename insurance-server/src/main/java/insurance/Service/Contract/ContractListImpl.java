package insurance.Service.Contract;

import insurance.Domain.Contract;
import insurance.Repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
@Transactional // data 변경하는 부분 이노테이션
public class ContractListImpl implements  ContractList {
    private final ContractRepository contractRepository;


    @Override
    public String getSuggestion(int clientIdx) {
        return contractRepository.getSuggestion(clientIdx);
    }
    @Override
    public String getSubscription(int clientIdx) {
        return contractRepository.getSubscription(clientIdx);
    }

    @Override
    public void subscriptionSign(int clientIndex) {
//        Sign i = new Sign();
//        i.run();
    }
    @Override
    public ArrayList<Contract> getAllContract(int clientIdx) {
        ArrayList<Contract> contractArrayList = (ArrayList<Contract>) contractRepository.findContractAll();
        ArrayList<Contract> contracts = new ArrayList<>();
        for(int i=0; i<contractArrayList.size(); i++){
            if(contractArrayList.get(i).getClient().getClientIdx() == clientIdx){
                contracts.add(contractArrayList.get(i));
            }
        }
        return contracts;
    }


}
