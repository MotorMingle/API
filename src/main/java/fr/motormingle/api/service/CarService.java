package fr.motormingle.api.service;

import fr.motormingle.api.entity.Car;
import fr.motormingle.api.exception.NotFoundException;
import fr.motormingle.api.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements FindService<Car, Long> {

    @Autowired
    private CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car with id " + id + " was not found"));
    }
}
