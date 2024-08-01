package com.example.To_Do.Repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.To_Do.Model.ToDoModel;


@Repository
public interface ToDoRepo extends JpaRepository<ToDoModel, Long> {

	
	   // Custom query to find ToDoModel by title(added)for check
    @Query("SELECT t FROM ToDoModel t WHERE t.title = :title")
    List<ToDoModel> findByTitleCustom(@Param("title") String title);
}
