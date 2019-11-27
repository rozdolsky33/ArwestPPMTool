package com.arwest.app.developer.ppmtool.services;

import com.arwest.app.developer.ppmtool.domain.Project;
import com.arwest.app.developer.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {


    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        //Logic

        return projectRepository.save(project);
    }


}
