package insurance.Domain.Insurance;

import lombok.Data;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Data
@DiscriminatorValue("industrial")
public class Industrial extends Insurance {
    private String workplaceAddress;
    private Integer businessNo;
    private String workplacePosition;
    private Integer safeGrade;
    private String workplaceType;
}