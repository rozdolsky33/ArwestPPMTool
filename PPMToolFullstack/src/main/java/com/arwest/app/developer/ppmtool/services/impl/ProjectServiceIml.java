package com.arwest.app.developer.ppmtool.services.impl;

import com.arwest.app.developer.ppmtool.domain.Project;
import com.arwest.app.developer.ppmtool.exceptions.ProjectIdException;
import com.arwest.app.developer.ppmtool.repositories.ProjectRepository;
import com.arwest.app.developer.ppmtool.services.ProjectService;
import com.arwest.app.developer.ppmtool.shared.dto.ProjectDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceIml implements ProjectService {


    @Autowired
    private ProjectRepository projectRepository;

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
    public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project ID '" + projectId + "'does not exists");
        }
        return project;
    }
    public Iterable<Project>findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = findProjectByIdentifier(projectId.toUpperCase());
        if (project == null){
            throw new ProjectIdException("Can not delete  project with ID: '" + projectId + "'does not exists");
        }
        projectRepository.delete(project);
    }

    public Project updateProjectById(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        try{
            projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Can not update  project with ID: '" + projectId + "'because it does not exists");
        }

        return project;
    }

}
