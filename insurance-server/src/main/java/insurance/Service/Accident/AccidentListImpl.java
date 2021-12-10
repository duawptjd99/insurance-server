package insurance.Service.Accident;


import insurance.Domain.Accident;
import insurance.Domain.Client;
import insurance.Domain.Insurance.Insurance;
import insurance.Form.AccidentForm;
import insurance.Repository.AccidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional() // data 변경하는 부분 이노테이션
public class AccidentListImpl implements AccidentList {
    private final ArrayList<Accident> accidentList;

    private final AccidentRepository accidentRepository;

    public boolean addAccident(AccidentForm accident) {
        Client client = accidentRepository.findOne(accident.getClientIdx());
        Optional<Insurance> insurance = accidentRepository.findByType(accident.getInsuranceType());
        accidentRepository.postAccident(accident, client, insurance);
        return true;
    }
    @Override  //client 사고리스트 가져오기
    public ArrayList<Accident> getMyAccident(int clientIdx) {
        ArrayList<Accident> accidentArrayList = accidentRepository.findAllAccident();
        ArrayList<Accident> accidents = new ArrayList<Accident>();
        for(int i=0; i< accidentArrayList.size(); i++){
            if(accidentArrayList.get(i).getClient().getClientIdx() == clientIdx){
                accidents.add(accidentArrayList.get(i));
            }
        }
        return accidents;
    }
    //client 소송진행 내용 저장
    @Override
    public int addLawsuit(AccidentForm accidentForm) {
        Accident accident = accidentRepository.findAccidentOne(accidentForm.getAccidentIdx());
        return accidentRepository.postLawsuit(accidentForm, accident);
    }





}