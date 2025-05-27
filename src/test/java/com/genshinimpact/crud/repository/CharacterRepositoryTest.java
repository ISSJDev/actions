package com.genshinimpact.crud.repository;

import com.genshinimpact.crud.model.Character;
import com.genshinimpact.crud.repository.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CharacterRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CharacterRepository repository;

    @Test
    public void should_find_no_characters_if_repository_is_empty() {
        List<Character> characters = repository.findAll();
        assertThat(characters).isEmpty();
    }

    @Test
    public void should_store_a_character() {
        Character character = repository.save(new Character(null, "Diluc", "Pyro", "Claymore", "5", "Mondstadt"));
        assertThat(character).hasFieldOrPropertyWithValue("name", "Diluc");
    }
}