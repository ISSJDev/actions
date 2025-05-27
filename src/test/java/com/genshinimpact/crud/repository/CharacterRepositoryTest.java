package com.genshinimpact.crud.repository;

import com.genshinimpact.crud.model.Character;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CharacterRepositoryTest {
    @Autowired
    private CharacterRepository characterRepository;

    @Test
    void testSaveCharacter() {
        Character character = new Character(null, "Diluc", "Pyro", "Claymore", "5", "Mondstadt");
        Character savedCharacter = characterRepository.save(character);

        assertNotNull(savedCharacter.getId());
        assertEquals("Diluc", savedCharacter.getName());
    }

    @Test
    void testFindAll() {
        Character character1 = new Character(null, "Diluc", "Pyro", "Claymore", "5", "Mondstadt");
        Character character2 = new Character(null, "Jean", "Anemo", "Sword", "5", "Mondstadt");

        characterRepository.save(character1);
        characterRepository.save(character2);

        List<Character> characters = characterRepository.findAll();

        assertEquals(2, characters.size());
    }

    @Test
    void testFindById() {
        Character character = new Character(null, "Diluc", "Pyro", "Claymore", "5", "Mondstadt");
        Character savedCharacter = characterRepository.save(character);

        Optional<Character> foundCharacter = characterRepository.findById(savedCharacter.getId());

        assertTrue(foundCharacter.isPresent());
        assertEquals("Diluc", foundCharacter.get().getName());
    }

    @Test
    void testDeleteById() {
        Character character = new Character(null, "Diluc", "Pyro", "Claymore", "5", "Mondstadt");
        Character savedCharacter = characterRepository.save(character);

        characterRepository.deleteById(savedCharacter.getId());

        Optional<Character> foundCharacter = characterRepository.findById(savedCharacter.getId());
        assertFalse(foundCharacter.isPresent());
    }
}