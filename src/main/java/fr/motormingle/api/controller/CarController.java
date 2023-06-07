package fr.motormingle.api.controller;

import fr.motormingle.api.controller.stereotype.GetController;
import fr.motormingle.api.entity.Car;
import fr.motormingle.api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController implements GetController<Car, Long> {

    @Autowired
    private CarService carService;

    @Override
    public ResponseEntity<List<Car>> getAll() {
        List<Car> cars = carService.findAll();
        return ResponseEntity.ok(cars);
    }

    @Override
    public ResponseEntity<Car> getById(Long id) {
        Car car = carService.findById(id);
        return ResponseEntity.ok(car);
    }
}
