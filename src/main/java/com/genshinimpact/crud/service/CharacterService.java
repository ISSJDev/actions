package com.genshinimpact.crud.service;

import com.genshinimpact.crud.dto.CharacterDTO;
import com.genshinimpact.crud.model.Character;

import java.util.List;

public interface CharacterService {
    List<CharacterDTO> getAllCharacters();
    CharacterDTO getCharacterById(Integer id);
    CharacterDTO createCharacter(CharacterDTO characterDTO);
    CharacterDTO updateCharacter(Integer id, CharacterDTO characterDTO);
    void deleteCharacter(Integer id);
}