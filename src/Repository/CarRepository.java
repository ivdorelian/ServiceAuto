package Repository;

import Domain.Car;
import Domain.CarValidator;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    private List<Car> cars = new ArrayList<>();
    private CarValidator validator;

    /**
     * Instantiates a repository.
     * @param validator the validator used by this repository.
     */
    public CarRepository(CarValidator validator) {
        this.validator = validator;
    }

    private Car findById(int id) {
        for (Car c : cars) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    /**
     * Adds a car to the repository.
     * @param car the car to add.
     * Raises RuntimeException if there already is a car with the given id or the car fails validation.
     */
    public void add(Car car) {
        if (findById(car.getId()) != null) {
            throw new RuntimeException("A car with that ID already exists!");
        }

        validator.validate(car);
        cars.add(car);
    }

    /**
     * Updates an existing car.
     * @param car the car to be updated.
     * Raises RuntimeException if there is no car with car's id or the new car fails validation.
     */
    public void update(Car car) {
        Car existingCar = findById(car.getId());
        if (existingCar == null) {
            throw new RuntimeException("There is no car with the given ID!");
        }

        validator.validate(car);
        for (int i = 0; i < cars.size(); ++i) {
            if (cars.get(i).getId() == existingCar.getId()) {
                cars.set(i, car);
                return;
            }
        }
    }

    /**
     * @return all cars in the repository.
     */
    public List<Car> getAll() {
        return cars;
    }
}
