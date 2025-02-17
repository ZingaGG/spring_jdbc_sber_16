package org.example.Repository;

import org.example.Model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByName(String name);

    List<Recipe> findByNameContaining(String name);
}
