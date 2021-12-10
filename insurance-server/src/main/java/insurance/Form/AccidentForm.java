package insurance.Form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccidentForm {
    private int accidentIdx;
    private String content;
    private String date;
    private int damagePrice;
    private int clientIdx;
    private String lawsuit;
    private String insuranceType;
    private Integer measuredPrice;
    private int employeeIdx;
}
