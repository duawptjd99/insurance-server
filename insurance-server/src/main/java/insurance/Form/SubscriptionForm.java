package insurance.Form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionForm {
    private int ContractIdx;
    private int EmployeeIdx;
    private int ClientIdx;
    private String content;
}
