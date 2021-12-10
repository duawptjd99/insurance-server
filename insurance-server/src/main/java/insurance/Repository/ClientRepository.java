package insurance.Repository;

import insurance.Domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor // final이 붙어있으면 constructor을 안 만들어도 됨.
public class ClientRepository {
    private final EntityManager em;

    //Client 저장
    public void postClient(Client client){
        em.persist(client);
    }
    //로그인 로직
    public Optional<Client> findByLoginId(String loginId){
        //findAll().stream() 루프를 돌면서 찾는다
        return findAllClient().stream().filter(
                c -> c.getId().equals(loginId))
                .findFirst();
    }
    public List<Client> findAllClient() {
        return em.createQuery("select c from Client c", Client.class).getResultList();
    }
    public Client findClientOne(int idx) {
        return em.find(Client.class, idx);
    }


//    public Client findByInfo(String name, String email) {
//
//    }


}