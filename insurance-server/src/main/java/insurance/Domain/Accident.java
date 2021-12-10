package insurance.Domain;

import insurance.Domain.Insurance.Insurance;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
public class Accident {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int accidentIdx;
    @Column(length = 5000000)
    private String content;
    private String date;
    private Integer damagePrice;
    private Integer measuredPrice;
    private String lawsuit;

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
