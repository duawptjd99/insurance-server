package insurance.Domain.Insurance;

import insurance.Domain.Employee;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Data
@Setter
@Inheritance(strategy = InheritanceType.JOINED) // join방식으로 상속을 매핑한다.
@DiscriminatorColumn // 하위 테이블의 구분 컬럼 생성(default = DTYPE)
public abstract class Insurance {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int insuranceIdx;
    private String restriction;
    private String period;
    private Integer fee;
    @Column(length = 5000000)
    private String guaranteedContent;
    private Integer rate;
    private Integer maximumReward;

    @ManyToOne
    @JoinColumn(name = "employeeIdx")
    private Employee employee;

//    @OneToMany
//    private List<Contract> insuranceContract;
//    @OneToMany
//    private List<Accident> insuranceAccident;

    @Transient
    public String getDecriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }
}

