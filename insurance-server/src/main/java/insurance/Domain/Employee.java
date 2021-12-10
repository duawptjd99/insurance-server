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
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int employeeIdx;
    private String id;
    private String pw;
    private String name;
    private Integer tel;
    private String role;

//    @OneToMany
//    private List<Contract> employeeContract;
//    @OneToMany
//    private List<Accident> employeeAccident;
//    @OneToMany
//    private List<Insurance> employeeInsurance;

}
