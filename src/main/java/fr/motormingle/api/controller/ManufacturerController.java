package fr.motormingle.api.controller;

import fr.motormingle.api.controller.stereotype.GetController;
import fr.motormingle.api.entity.Manufacturer;
import fr.motormingle.api.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manufacturer")
public class ManufacturerController implements GetController<Manufacturer, Long> {

    @Autowired
    private ManufacturerService manufacturerService;

    @Override
    public ResponseEntity<List<Manufacturer>> getAll() {
        var manufacturers = manufacturerService.findAll();
        return ResponseEntity.ok(manufacturers);
    }

    @Override
    public ResponseEntity<Manufacturer> getById(Long id) {
        var manufacturer = manufacturerService.findById(id);
        return ResponseEntity.ok(manufacturer);
    }
}
