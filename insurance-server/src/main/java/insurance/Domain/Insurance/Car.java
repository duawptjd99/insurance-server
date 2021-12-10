package insurance.Domain.Insurance;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@DiscriminatorValue("car")
public class Car extends Insurance {
    private Integer carNO;
    private Integer accidentCount;
}
