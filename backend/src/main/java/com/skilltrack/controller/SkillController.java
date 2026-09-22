package com.skilltrack.controller;

import com.skilltrack.model.Skill;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SkillController {

    private final List<Skill> skills = new ArrayList<>();

    @GetMapping("/skills")
    public List<Skill> getSkills() {
        return skills;
    }

    @PostMapping("/skills")
    public Skill createSkill(@RequestBody Skill skill) {
        skills.add(skill);
        return skill;
    }
}
