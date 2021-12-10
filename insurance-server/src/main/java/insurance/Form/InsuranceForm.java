package insurance.Form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsuranceForm {
    private String Dtype;
    private Integer fee;
    private Integer rate;
    private String period;
    private Integer maximumReward;
    private String restriction;
    private String guaranteedContent;
    private int employeeIdx;
    //car
    private Integer accidentCount;
    private Integer carno;
    //fire
    private Integer buildingCount;
    private Integer buildingPrice;
    //industrial
    private Integer businessNo;
    private Integer safeGrade;
    private String workplaceType;
    private String workplacePosition;
    private String workplaceAddress;
    //life
    private Integer healthGrade;


}

