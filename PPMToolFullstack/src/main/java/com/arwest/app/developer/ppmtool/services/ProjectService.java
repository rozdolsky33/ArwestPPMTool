package com.arwest.app.developer.ppmtool.services;


import com.arwest.app.developer.ppmtool.domain.Project;
import com.arwest.app.developer.ppmtool.shared.dto.ProjectDto;

public interface ProjectService {

    ProjectDto createProject(ProjectDto project);
    ProjectDto getProjectById(String projectId);
    Iterable<Project> getAllProjects();
    ProjectDto updateProject(String projectId, ProjectDto projectDto);
}
