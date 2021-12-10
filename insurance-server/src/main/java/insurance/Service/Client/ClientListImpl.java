package insurance.Service.Client;


import insurance.Domain.Client;
import insurance.Domain.Insurance.Insurance;
import insurance.Repository.ClientRepository;
import insurance.Repository.InsuranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true) // data 변경하는 부분 이노테이션
public class ClientListImpl implements ClientList {
    private final ClientRepository clientRepository;
    private final InsuranceRepository insuranceRepository;

    @Transactional
    @Override
    public String signUp(Client client) {
        clientRepository.postClient(client);
        return null;
    }
    @Override
    public Client signIn(String id, String pw) {
        return clientRepository.findByLoginId(id)
                .filter(c -> c.getPw().equals(pw))
                .orElse(null);
    }

    @Override
    public Client findId(String name, String email) {
        ArrayList<Client> clientArrayList = (ArrayList<Client>) clientRepository.findAllClient();
        Client client = new Client();
        for (int i=0; i < clientArrayList.size(); i++){
            if(clientArrayList.get(i).getName().equals(name) && clientArrayList.get(i).getEmail().equals(email)){
                client = clientArrayList.get(i);
            }
        }
        return client;
    }

    @Override
    public Client findPw(String id, String email) {
        ArrayList<Client> clientArrayList = (ArrayList<Client>) clientRepository.findAllClient();
        Client client = new Client();
        for (int i=0; i < clientArrayList.size(); i++){
            if(clientArrayList.get(i).getId().equals(id) && clientArrayList.get(i).getEmail().equals(email)){
                client = clientArrayList.get(i);
            }
        }
        return client;
    }

    @Override
    public Boolean checkId(String id) {
        ArrayList<Client> clientArrayList = (ArrayList<Client>) clientRepository.findAllClient();
        Boolean haveId = false;
        for(int i =0 ; i< clientArrayList.size(); i++){
            if(clientArrayList.get(i).getId().equals(id)){
                haveId = true;
            }
        }
        return haveId;
    }

    @Override
    public ArrayList<Insurance> getAllInsurance() {
        return insuranceRepository.findAllInsurance();
    }

}
