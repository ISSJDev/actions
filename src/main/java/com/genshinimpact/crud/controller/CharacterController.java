package com.genshinimpact.crud.controller;

import com.genshinimpact.crud.dto.CharacterDTO;
import com.genshinimpact.crud.service.CharacterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getAllCharacters() {
        return ResponseEntity.ok(characterService.getAllCharacters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable Integer id) {
        return ResponseEntity.ok(characterService.getCharacterById(id));
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> createCharacter(@Valid @RequestBody CharacterDTO characterDTO) {
        return new ResponseEntity<>(characterService.createCharacter(characterDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> updateCharacter(
            @PathVariable Integer id,
            @Valid @RequestBody CharacterDTO characterDTO) {
        return ResponseEntity.ok(characterService.updateCharacter(id, characterDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Integer id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }
}