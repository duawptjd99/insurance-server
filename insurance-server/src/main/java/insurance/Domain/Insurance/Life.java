package insurance.Domain.Insurance;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Data
@DiscriminatorValue("life")
public class Life extends Insurance {
    private Integer healthGrade;

}