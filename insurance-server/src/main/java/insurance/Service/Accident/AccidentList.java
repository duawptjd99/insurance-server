package insurance.Service.Accident;

import insurance.Domain.Accident;
import insurance.Form.AccidentForm;

import java.util.ArrayList;

public interface AccidentList {
    public boolean addAccident(AccidentForm accident);
    public ArrayList<Accident> getMyAccident(int clientIdx);
    public int addLawsuit(AccidentForm accidentForm);





}
