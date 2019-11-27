package com.arwest.app.developer.ppmtool.web;

import com.arwest.app.developer.ppmtool.domain.Project;
import com.arwest.app.developer.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<Project>createNewProject(@RequestBody Project project){

        Project project1 = projectService.saveOrUpdateProject(project);

        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }


}
