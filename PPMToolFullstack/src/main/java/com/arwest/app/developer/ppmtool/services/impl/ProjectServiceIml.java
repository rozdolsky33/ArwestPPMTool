package com.arwest.app.developer.ppmtool.services.impl;

import com.arwest.app.developer.ppmtool.domain.Project;
import com.arwest.app.developer.ppmtool.exceptions.ProjectIdException;
import com.arwest.app.developer.ppmtool.repositories.ProjectRepository;
import com.arwest.app.developer.ppmtool.services.ProjectService;
import com.arwest.app.developer.ppmtool.shared.dto.ProjectDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProjectServiceIml implements ProjectService {


    @Autowired
    private ProjectRepository projectRepository;


    public ProjectDto getProjectById(String projectId){

        ProjectDto returnValue = new ProjectDto();
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project ID '" + projectId + "'does not exists");
        }
        BeanUtils.copyProperties(project, returnValue);

        return returnValue;
    }

    @Override
    public Iterable<Project> getAllProjects() {
        return projectRepository.findAll();
    }
//Service
    public ProjectDto createProject(ProjectDto project){

         try {
             Project newProject = new Project();
             BeanUtils.copyProperties(project, newProject);
             newProject.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

             Project storedProject = projectRepository.save(newProject);

             ProjectDto returnValue = new ProjectDto();
             BeanUtils.copyProperties(storedProject, returnValue);

             return returnValue;

         }catch (Exception e){
             throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase()+ "'already exists");
         }
    }
    @Override
    public ProjectDto updateProject(String projectId, ProjectDto project) {

        ProjectDto returnValue = new ProjectDto();

        Project projectEntity = projectRepository.findByProjectIdentifier(projectId);

        if (projectEntity == null) throw new ProjectIdException("Project with this ID Not Found");

        projectEntity.setProjectName(project.getProjectName());
        projectEntity.setDescription(project.getDescription());
        projectEntity.setEnd_date(project.getEnd_date());
        projectEntity.setUpdated_At(new Date());

        Project updatedProject = projectRepository.save(projectEntity);
        returnValue = new ModelMapper().map(updatedProject, ProjectDto.class);

        return returnValue;
    }

    @Override
    public void deleteProject(String projectId) {

        Project projectEntity = projectRepository.findByProjectIdentifier(projectId);

        if (projectEntity == null) throw new ProjectIdException("Project with this ID Not Found");

        projectRepository.delete(projectEntity);

    }

}