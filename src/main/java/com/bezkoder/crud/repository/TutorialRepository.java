package com.bezkoder.crud.repository;

import com.bezkoder.crud.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    public List<Tutorial> findByPublished(boolean published);

    public List<Tutorial> findByTitleContaining(String title);


}
