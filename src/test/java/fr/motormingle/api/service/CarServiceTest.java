package fr.motormingle.api.service;

import fr.motormingle.api.entity.Car;
import fr.motormingle.api.entity.Manufacturer;
import fr.motormingle.api.exception.NotFoundException;
import fr.motormingle.api.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    private Car car;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        car = new Car();
        car.setId(1L);
        car.setModel("Model X");
        car.setYear(2020);
        car.setHorsePower(320);
        Manufacturer manufacturer = new Manufacturer(); // Make sure you have a Manufacturer class defined.
        manufacturer.setId(1L);
        car.setManufacturer(manufacturer);
        car.setCapacity(2);
    }

    @Test
    void testFindAll() {
        List<Car> cars = Collections.singletonList(car);
        when(carRepository.findAll()).thenReturn(cars);

        List<Car> result = carService.findAll();
        assertEquals(cars, result);
    }

    @Test
    void testFindById() {
        when(carRepository.findById(anyLong())).thenReturn(Optional.of(car));

        Car result = carService.findById(1L);
        assertEquals(car, result);
    }

    @Test
    void testFindByIdNotFound() {
        when(carRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            carService.findById(2L);
        });
    }
}
