package insurance.Service.Contract;

import insurance.Domain.Contract;

import java.util.ArrayList;

public interface ContractList {
    public String getSuggestion(int clientIdx);
    public String getSubscription(int clientIdx);
    public void subscriptionSign(int clientIndex);
    public ArrayList<Contract> getAllContract(int clientIdx);
}
