package com.genshinimpact.crud.repository;

import com.genshinimpact.crud.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
}