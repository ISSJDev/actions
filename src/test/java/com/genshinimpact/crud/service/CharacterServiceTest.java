package com.genshinimpact.crud.service;

import com.genshinimpact.crud.dto.CharacterDTO;
import com.genshinimpact.crud.exception.CharacterNotFoundException;
import com.genshinimpact.crud.model.Character;
import com.genshinimpact.crud.repository.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {
    @Mock
    private CharacterRepository characterRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CharacterServiceImpl characterService;

    private Character character;
    private CharacterDTO characterDTO;

    @BeforeEach
    void setUp() {
        character = new Character(1, "Diluc", "Pyro", "Claymore", "5", "Mondstadt");
        characterDTO = new CharacterDTO(1, "Diluc", "Pyro", "Claymore", "5", "Mondstadt");
    }

    @Test
    void getAllCharacters() {
        // Arrange
        when(characterRepository.findAll()).thenReturn(Arrays.asList(character));
        when(modelMapper.map(character, CharacterDTO.class)).thenReturn(characterDTO);

        // Act
        List<CharacterDTO> result = characterService.getAllCharacters();

        // Assert
        assertEquals(1, result.size());
        assertEquals(characterDTO, result.get(0));
        verify(characterRepository, times(1)).findAll();
    }

    @Test
    void getCharacterById() {
        // Arrange
        when(characterRepository.findById(1)).thenReturn(Optional.of(character));
        when(modelMapper.map(character, CharacterDTO.class)).thenReturn(characterDTO);

        // Act
        CharacterDTO result = characterService.getCharacterById(1);

        // Assert
        assertEquals(characterDTO, result);
        verify(characterRepository, times(1)).findById(1);
    }

    @Test
    void getCharacterByIdNotFound() {
        // Arrange
        when(characterRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CharacterNotFoundException.class, () -> characterService.getCharacterById(1));
        verify(characterRepository, times(1)).findById(1);
    }

    @Test
    void createCharacter() {
        // Arrange
        when(modelMapper.map(characterDTO, Character.class)).thenReturn(character);
        when(characterRepository.save(character)).thenReturn(character);
        when(modelMapper.map(character, CharacterDTO.class)).thenReturn(characterDTO);

        // Act
        CharacterDTO result = characterService.createCharacter(characterDTO);

        // Assert
        assertEquals(characterDTO, result);
        verify(characterRepository, times(1)).save(character);
    }

    @Test
    void updateCharacter() {
        // Arrange
        when(characterRepository.findById(1)).thenReturn(Optional.of(character));
        when(characterRepository.save(character)).thenReturn(character);
        when(modelMapper.map(character, CharacterDTO.class)).thenReturn(characterDTO);

        // Act
        CharacterDTO result = characterService.updateCharacter(1, characterDTO);

        // Assert
        assertEquals(characterDTO, result);
        verify(characterRepository, times(1)).findById(1);
        verify(characterRepository, times(1)).save(character);
    }

    @Test
    void updateCharacterNotFound() {
        // Arrange
        when(characterRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CharacterNotFoundException.class, () -> characterService.updateCharacter(1, characterDTO));
        verify(characterRepository, times(1)).findById(1);
        verify(characterRepository, never()).save(any());
    }

    @Test
    void deleteCharacter() {
        // Arrange
        when(characterRepository.existsById(1)).thenReturn(true);

        // Act
        characterService.deleteCharacter(1);

        // Assert
        verify(characterRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteCharacterNotFound() {
        // Arrange
        when(characterRepository.existsById(1)).thenReturn(false);

        // Act & Assert
        assertThrows(CharacterNotFoundException.class, () -> characterService.deleteCharacter(1));
        verify(characterRepository, times(1)).existsById(1);
        verify(characterRepository, never()).deleteById(1);
    }
}