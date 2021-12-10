package insurance.Repository;

import insurance.Domain.Accident;
import insurance.Domain.Client;
import insurance.Domain.Employee;
import insurance.Domain.Insurance.Insurance;
import insurance.Form.AccidentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccidentRepository {
    private final EntityManager em;

    //client 사고 접수 로직
    public void postAccident(AccidentForm accidentData, Client client, Optional<Insurance> insurance) {
        Accident accident = new Accident();
        accident.setContent(accidentData.getContent());
        accident.setDate(accidentData.getDate());
        accident.setDamagePrice(accidentData.getDamagePrice());
        accident.setClient(client);
        accident.setInsurance(insurance.get());
        em.persist(accident);
    }

    //client 소송진행 내용 저장
    public int postLawsuit(AccidentForm accidentForm, Accident accident) {
        accident.setLawsuit(accidentForm.getLawsuit());
        return accident.getAccidentIdx();
    }

    //handler 사고승인
    public int finishAccident(AccidentForm accidentForm, Accident accident, Employee employee) {
        accident.setEmployee(employee);
        accident.setMeasuredPrice(accidentForm.getMeasuredPrice());
        return accident.getAccidentIdx();
    }

    public Optional<Insurance> findByType(String insuranceType){
        return findInsuranceAll().stream().filter(
                        i -> i.getDecriminatorValue().equals(insuranceType))
                .findFirst();
    }
    public List<Insurance> findInsuranceAll() {
        return em.createQuery("select i from Insurance i", Insurance.class).getResultList();
    }
    public Client findOne(int idx) {
        return em.find(Client.class, idx);
    }

    public ArrayList<Accident> findAllAccident() {
        return (ArrayList<Accident>) em.createQuery("select c from Accident c", Accident.class).getResultList();
    }


    public Employee findEmployeeOne(int employeeIdx) {
        return em.find(Employee.class, employeeIdx);
    }

    public Accident findAccidentOne(int accidentIdx) {
        return em.find(Accident.class, accidentIdx);
    }



}
