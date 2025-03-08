package controller;

import entity.Resource;
import service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    

    @GetMapping("/by-skills-and-experience")
    public List<Resource> getResourcesBySkillsAndExperience(
            @RequestParam Set<String> skills,
            @RequestParam int maxExperience) {
        return resourceService.getResourcesBySkillsAndExperience(skills, maxExperience);
    }
}