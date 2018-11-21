package com.galvanize.gamecharacter.controller;

import com.galvanize.gamecharacter.model.GameCharacter;

import com.galvanize.gamecharacter.service.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.Random;

@ControllerAdvice // use for ErrorDetail
@RestController
@RequestMapping("/characterservice")
public class CharacterController {
    public final CharacterService characterService;

    public CharacterController(CharacterService characterService){
        this.characterService = characterService;

    }

    @PostMapping("/character/gen/{name}/{classname}")
    public ResponseEntity<Object> addCharacter(@RequestBody GameCharacter character, @PathVariable("name") String name, @PathVariable("classname") String classname){
        return this.characterService.addCharacter(character, name, classname);
    }


    @GetMapping("/character/{classname}")

    public Iterable<GameCharacter> getAllCharacterByClass(@PathVariable String classname){
        return this.characterService.getAllCharacterByClass(classname);
    }




}
