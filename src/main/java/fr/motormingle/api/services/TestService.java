package fr.motormingle.api.services;

import fr.motormingle.api.dtos.test.PostResponse;
import fr.motormingle.api.models.Test;
import fr.motormingle.api.repositories.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public Test postTest(Test test) {
        return this.testRepository.save(test);
    }

    public List<Test> getTests() {
        return this.testRepository.findAll();
    }

    public Test getTest(Long id) {
        return this.testRepository.findById(id).orElseThrow();
    }
}
