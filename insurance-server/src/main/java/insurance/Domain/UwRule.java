package insurance.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
public class UwRule {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int ruleIdx;
    @Column(length = 5000000)
    private String uwEstablish;

    @ManyToOne
    @JoinColumn(name = "employeeIdx")
    private Employee employee;
}
