package com.project.traplaner.travelBoard.repository;

import com.project.traplaner.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

}
