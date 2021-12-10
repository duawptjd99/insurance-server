package insurance.Domain;

import insurance.Domain.Insurance.Insurance;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Data
public class Contract {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int contractIdx;

    @Column(length = 5000000)
    private String suggestion;
    @Column(length = 5000000)
    private String subscription;
    private Date created;
    private Boolean checkForm = false;
    private Boolean checkMoney = false;
    private Boolean finalContract = false;

    @ManyToOne
    @JoinColumn(name = "clientIdx")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "employeeIdx")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "insuranceIdx")
    private Insurance insurance;
}
