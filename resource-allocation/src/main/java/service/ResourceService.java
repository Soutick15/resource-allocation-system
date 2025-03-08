package service;



import entity.Resource;
import entity.Skill;
import repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public List<Resource> getResourcesBySkillsAndExperience(Set<String> requiredSkills, int maxExperience) {
        return resourceRepository.findAll().stream()
                .filter(resource -> resource.getExperience() <= maxExperience)
                .filter(resource -> resource.getSkills().stream()
                        .map(Skill::getSkillName)
                        .collect(Collectors.toSet())
                        .containsAll(requiredSkills))
                .collect(Collectors.toList());
    }
}
