package Service;

import Domain.Car;
import Domain.StandReportViewModel;
import Repository.CarRepository;

import java.util.*;

public class CarService {

    private CarRepository repository;

    /**
     * Instantiates a car service.
     * @param repository the repository used by this service.
     */
    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    /**
     * Adds a car to the repair shop.
     * @param id the car id.
     * @param standNumber the stand number.
     * @param licensePlateNumber the license plate number in format JJNNLLL.
     * @param numberOfDays the number of days the car will be in service for.
     * Raises RuntimeException if the stand number is taken of there are other errors.
     */
    public void enterRepairShop(int id, int standNumber, String licensePlateNumber, int numberOfDays) {

        // ne asiguram ca standNumber nu este ocupat.
        for (Car c : repository.getAll()) {
            if (c.getStandNumber() == standNumber && !c.hasLeftService()) {
                throw new RuntimeException("That stand is already taken by another car!");
            }
        }
        Car car = new Car(id, standNumber, licensePlateNumber, numberOfDays);
        repository.add(car);
    }

    /**
     * Removes a car from the repair shop.
     * @param standNumber the stand number to clear.
     * @param report the work report.
     * @param billedPrice the price that the customer was billed.
     * Raises RuntimeException if the stand number is empty.
     */
    public void leaveRepairShop(int standNumber, String report, double billedPrice) {

        // ne asiguram ca standNumber este ocupat.
        for (Car c : repository.getAll()) {
            if (c.getStandNumber() == standNumber && !c.hasLeftService()) {
                c.setLeftService(true);
                c.setReport(report);
                c.setBilledPrice(billedPrice);
                repository.update(c); // merge si fara? de ce?
                return;
            }
        }
        throw new RuntimeException("There is no car on the given stand!");
    }

    /**
     * @return A list of view models ordered descendingly by average stand prices.
     */
    public List<StandReportViewModel> getStandsReport() {

        // grupam preturile dupa numarul de stand
        Map<Integer, List<Double>> standGroups = new HashMap<>();
        for (Car c : repository.getAll()) {
            if (c.hasLeftService()) {
                int standNumber = c.getStandNumber();
                if (standGroups.containsKey(standNumber)) {
                    standGroups.get(standNumber).add(c.getBilledPrice());
                } else {
                    List<Double> firstPrice = new ArrayList<>();
                    firstPrice.add(c.getBilledPrice());
                    standGroups.put(standNumber, firstPrice);
                }
            }
        }

        List<StandReportViewModel> result = new ArrayList<>();
        for (Integer standNumber : standGroups.keySet()) {
            double average = 0;
            for (Double price : standGroups.get(standNumber)) {
                average += price;
            }
            average /= standGroups.get(standNumber).size();

            result.add(new StandReportViewModel(standNumber, average));
        }

        result.sort((v1, v2) -> v1.getPriceAverage() > v2.getPriceAverage() ? -1 : 0);
        return result;
    }
}
