package fr.motormingle.api.controllers;

import fr.motormingle.api.dtos.test.TestPost;
import fr.motormingle.api.dtos.test.PostResponse;
import fr.motormingle.api.models.Test;
import fr.motormingle.api.services.TestService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @PostMapping
    public PostResponse postTest(@RequestBody TestPost testPost) {
        Test response = this.testService.postTest(new Test(testPost.name()));
        return new PostResponse(response.getId(), response.getName());
    }

    @GetMapping
    public List<Test> getTests() {
        return this.testService.getTests();
    }

    @GetMapping("/{id}")
    public Test getTest(@PathVariable Long id) {
        return this.testService.getTest(id);
    }
}
