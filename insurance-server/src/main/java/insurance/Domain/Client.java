package insurance.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Data
public class Client {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int clientIdx;

    private String id;
    private String pw;
    private String name;
    private String email;
    private Integer ssn;
    private Integer tel;
    private String address;
    private Integer age;
    private String sex;
    private boolean haveSuggest = false;
    private boolean haveSubscription = false;


//    @OneToMany
//    private List<Contract> clientContract;
//    @OneToMany
//    private List<Accident> clientAccident;
}