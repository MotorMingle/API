package fr.motormingle.api.controller;

import fr.motormingle.api.controller.stereotype.GetController;
import fr.motormingle.api.dto.mingler.get.MinglerTagGet;
import fr.motormingle.api.entity.Mingler;
import fr.motormingle.api.service.MinglerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mingler")
public class MinglerController implements GetController<Mingler, String> {

    @Autowired
    private MinglerService minglerService;

    @Override
    public ResponseEntity<List<Mingler>> getAll() {
        return ResponseEntity.ok(minglerService.findAll());
    }

    @Override
    public ResponseEntity<Mingler> getById(String id) {
        return ResponseEntity.ok(minglerService.findById(id));
    }

    @GetMapping("/{id}/tag")
    public ResponseEntity<MinglerTagGet> getTagById(@PathVariable String id) {
        return ResponseEntity.ok(minglerService.findTagById(id));
    }
}
