package com.arwest.app.developer.ppmtool.services;

import com.arwest.app.developer.ppmtool.domain.Project;
import com.arwest.app.developer.ppmtool.exceptions.ProjectIdException;
import com.arwest.app.developer.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {


    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

         try {
             project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
             return projectRepository.save(project);
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


}