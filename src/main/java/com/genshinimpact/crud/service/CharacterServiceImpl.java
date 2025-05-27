package com.genshinimpact.crud.service;

import com.genshinimpact.crud.dto.CharacterDTO;
import com.genshinimpact.crud.exception.CharacterNotFoundException;
import com.genshinimpact.crud.model.Character;
import com.genshinimpact.crud.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CharacterDTO> getAllCharacters() {
        return characterRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CharacterDTO getCharacterById(Integer id) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException("Character not found with id: " + id));
        return convertToDTO(character);
    }

    @Override
    public CharacterDTO createCharacter(CharacterDTO characterDTO) {
        Character character = convertToEntity(characterDTO);
        Character savedCharacter = characterRepository.save(character);
        return convertToDTO(savedCharacter);
    }

    @Override
    public CharacterDTO updateCharacter(Integer id, CharacterDTO characterDTO) {
        Character existingCharacter = characterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException("Character not found with id: " + id));

        modelMapper.map(characterDTO, existingCharacter);
        Character updatedCharacter = characterRepository.save(existingCharacter);
        return convertToDTO(updatedCharacter);
    }

    @Override
    public void deleteCharacter(Integer id) {
        if (!characterRepository.existsById(id)) {
            throw new CharacterNotFoundException("Character not found with id: " + id);
        }
        characterRepository.deleteById(id);
    }

    private CharacterDTO convertToDTO(Character character) {
        return modelMapper.map(character, CharacterDTO.class);
    }

    private Character convertToEntity(CharacterDTO characterDTO) {
        return modelMapper.map(characterDTO, Character.class);
    }
}