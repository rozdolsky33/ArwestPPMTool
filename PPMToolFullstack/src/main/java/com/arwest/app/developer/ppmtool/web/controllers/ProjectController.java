package com.arwest.app.developer.ppmtool.web.controllers;

import com.arwest.app.developer.ppmtool.domain.Project;
import com.arwest.app.developer.ppmtool.services.MapValidationErrorService;
import com.arwest.app.developer.ppmtool.services.ProjectService;
import com.arwest.app.developer.ppmtool.shared.dto.ProjectDto;
import com.arwest.app.developer.ppmtool.web.ui.request.ProjectRequestModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {


    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?>createNewProject(@Valid @RequestBody ProjectRequestModel project, BindingResult result){

        ResponseEntity<?>errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        ProjectDto projectDto = new ProjectDto();
        BeanUtils.copyProperties(project, projectDto);

        ProjectDto createProject = projectService.createProject(projectDto);

        return new ResponseEntity<>(createProject, HttpStatus.CREATED);
    }
    @GetMapping("/{projectId}")
    public ResponseEntity<?>getProjectById(@PathVariable String projectId){

        ProjectDto project = projectService.getProjectById(projectId);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project>getAllProjects(){
        return projectService.getAllProjects();
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<?>updateProject(@PathVariable String projectId, @RequestBody ProjectRequestModel project, BindingResult result){

        ResponseEntity<?>errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        Project returnValue = new Project();

        ProjectDto projectDto = new ProjectDto();
        BeanUtils.copyProperties(project, projectDto);

        ProjectDto updateProject = projectService.updateProject(projectId, projectDto);
        BeanUtils.copyProperties(updateProject, returnValue);

        return new ResponseEntity<>(updateProject, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){

        projectService.deleteProject(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
