package com.genshinimpact.crud.controller;

import com.genshinimpact.crud.dto.CharacterDTO;
import com.genshinimpact.crud.service.CharacterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharacterControllerTest {
    @Mock
    private CharacterService characterService;

    @InjectMocks
    private CharacterController characterController;

    private CharacterDTO characterDTO;

    @BeforeEach
    void setUp() {
        characterDTO = new CharacterDTO(1, "Diluc", "Pyro", "Claymore", "5", "Mondstadt");
    }

    @Test
    void getAllCharacters() {
        // Arrange
        List<CharacterDTO> characters = Arrays.asList(characterDTO);
        when(characterService.getAllCharacters()).thenReturn(characters);

        // Act
        ResponseEntity<List<CharacterDTO>> response = characterController.getAllCharacters();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(characters, response.getBody());
        verify(characterService, times(1)).getAllCharacters();
    }

    @Test
    void getCharacterById() {
        // Arrange
        when(characterService.getCharacterById(1)).thenReturn(characterDTO);

        // Act
        ResponseEntity<CharacterDTO> response = characterController.getCharacterById(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(characterDTO, response.getBody());
        verify(characterService, times(1)).getCharacterById(1);
    }

    @Test
    void createCharacter() {
        // Arrange
        when(characterService.createCharacter(characterDTO)).thenReturn(characterDTO);

        // Act
        ResponseEntity<CharacterDTO> response = characterController.createCharacter(characterDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(characterDTO, response.getBody());
        verify(characterService, times(1)).createCharacter(characterDTO);
    }

    @Test
    void updateCharacter() {
        // Arrange
        when(characterService.updateCharacter(1, characterDTO)).thenReturn(characterDTO);

        // Act
        ResponseEntity<CharacterDTO> response = characterController.updateCharacter(1, characterDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(characterDTO, response.getBody());
        verify(characterService, times(1)).updateCharacter(1, characterDTO);
    }

    @Test
    void deleteCharacter() {
        // Arrange
        doNothing().when(characterService).deleteCharacter(1);

        // Act
        ResponseEntity<Void> response = characterController.deleteCharacter(1);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(characterService, times(1)).deleteCharacter(1);
    }
}