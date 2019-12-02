package com.arwest.app.developer.ppmtool.repositories;

import com.arwest.app.developer.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByProjectIdentifier(String projectId);
    Iterable<Project> findAll();

}
