package org.example.SpringAllProjectSite.repos;

import org.example.SpringAllProjectSite.database.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepo extends CrudRepository<Project, Long> {
    List<Project> findByLang(String lang);
    Project findByName(String name);

}
