package insurance.Repository;

import insurance.Domain.Contract;
import insurance.Domain.Employee;
import insurance.Domain.Insurance.*;
import insurance.Form.InsuranceForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class InsuranceRepository {
    private final EntityManager em;

    public int postInsurance(InsuranceForm insuranceForm) {
        int idx = 0;
        switch (insuranceForm.getDtype()){
            case "car" :
                Car car = new Car();
                Employee employee = findEmployeeOne(insuranceForm.getEmployeeIdx());
                car.setEmployee(employee);
                car.setRestriction(insuranceForm.getRestriction());
                car.setPeriod(insuranceForm.getPeriod());
                car.setFee(insuranceForm.getFee());
                car.setGuaranteedContent(insuranceForm.getGuaranteedContent());
                car.setRate(insuranceForm.getRate());
                car.setMaximumReward(insuranceForm.getMaximumReward());
                car.setCarNO(insuranceForm.getCarno()); //여기서부턴 따로
                car.setAccidentCount(insuranceForm.getAccidentCount());
                em.persist(car);
                idx = car.getInsuranceIdx();
                break;
            case "fire" :
                Fire fire = new Fire();
                Employee employee2 = findEmployeeOne(insuranceForm.getEmployeeIdx());
                fire.setEmployee(employee2);
                fire.setRestriction(insuranceForm.getRestriction());
                fire.setPeriod(insuranceForm.getPeriod());
                fire.setFee(insuranceForm.getFee());
                fire.setGuaranteedContent(insuranceForm.getGuaranteedContent());
                fire.setRate(insuranceForm.getRate());
                fire.setMaximumReward(insuranceForm.getMaximumReward());
                fire.setBuildingPrice(insuranceForm.getBuildingPrice());
                fire.setBuildingCount(insuranceForm.getBuildingCount());
                em.persist(fire);
                idx = fire.getInsuranceIdx();
                break;
            case "industrial" :
                Industrial industrial = new Industrial();
                Employee employee3 = findEmployeeOne(insuranceForm.getEmployeeIdx());
                industrial.setEmployee(employee3);
                industrial.setRestriction(insuranceForm.getRestriction());
                industrial.setPeriod(insuranceForm.getPeriod());
                industrial.setFee(insuranceForm.getFee());
                industrial.setGuaranteedContent(insuranceForm.getGuaranteedContent());
                industrial.setRate(insuranceForm.getRate());
                industrial.setMaximumReward(insuranceForm.getMaximumReward());
                industrial.setWorkplaceType(insuranceForm.getWorkplaceAddress());
                industrial.setBusinessNo(insuranceForm.getBusinessNo());
                industrial.setWorkplaceAddress(insuranceForm.getWorkplacePosition());
                industrial.setSafeGrade(insuranceForm.getSafeGrade());
                industrial.setWorkplacePosition(insuranceForm.getWorkplaceAddress());
                em.persist(industrial);
                idx = industrial.getInsuranceIdx();
                break;
            case "life" :
                Life life = new Life();
                Employee employee4 = findEmployeeOne(insuranceForm.getEmployeeIdx());
                life.setEmployee(employee4);
                life.setRestriction(insuranceForm.getRestriction());
                life.setPeriod(insuranceForm.getPeriod());
                life.setFee(insuranceForm.getFee());
                life.setGuaranteedContent(insuranceForm.getGuaranteedContent());
                life.setRate(insuranceForm.getRate());
                life.setMaximumReward(insuranceForm.getMaximumReward());
                life.setHealthGrade(insuranceForm.getHealthGrade());
                em.persist(life);
                idx = life.getInsuranceIdx();
                break;
        }
        return idx;
    }
    public Employee findEmployeeOne(int idx){
        return em.find(Employee.class, idx);
    }

    public Contract findContractOne(int idx){
        return em.find(Contract.class, idx);
    }


    public ArrayList<Insurance> findAllInsurance() {
        return (ArrayList<Insurance>) em.createQuery("select i from Insurance i", Insurance.class).getResultList();
    }
}

