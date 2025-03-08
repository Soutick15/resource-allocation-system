package service;

import entity.Resource;
import entity.Skill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.ResourceRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ResourceServiceTest {

    @Mock
    private ResourceRepository resourceRepository;

    @InjectMocks
    private ResourceService resourceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void testGetResourcesBySkillsAndExperience() {
        // Create sample data
        Resource resource1 = new Resource();
        resource1.setResourceId(1L);
        resource1.setResourceName("Dennis");
        resource1.setExperience(4);
        resource1.setSkills(new HashSet<>(Arrays.asList(
                new Skill(1L, "Java", resource1),
                new Skill(2L, "Redis", resource1),
                new Skill(3L, "Javascript", resource1)
        )));

        Resource resource2 = new Resource();
        resource2.setResourceId(2L);
        resource2.setResourceName("Thompson");
        resource2.setExperience(7);
        resource2.setSkills(new HashSet<>(Arrays.asList(
                new Skill(4L, "Java", resource2),
                new Skill(5L, "React", resource2),
                new Skill(6L, "Angular", resource2)
        )));

        // Mock the repository to return the sample data
        when(resourceRepository.findAll()).thenReturn(Arrays.asList(resource1, resource2));

        // Define the required skills and max experience
        Set<String> requiredSkills = new HashSet<>(Arrays.asList("Java", "Redis", "Javascript"));
        int maxExperience = 10;

        // Call the service method
        List<Resource> result = resourceService.getResourcesBySkillsAndExperience(requiredSkills, maxExperience);

        // Verify the result
        assertEquals(1, result.size());
        assertEquals("Dennis", result.get(0).getResourceName());
    }
}