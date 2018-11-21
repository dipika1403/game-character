package com.galvanize.gamecharacter.model;

import javax.persistence.*;


@Entity
public class GameCharacter {

    public GameCharacter(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "characterName")
    public String characterName;

    @Column(name = "characterClass")
    public String characterClass;


    @Column(name = "CharacterInt")
    public int CharacterInt;

    @Column(name = "CharacterWis")
    public int CharacterWis;

    @Column(name = "CharacterCha")
    public int CharacterCha;

    @Column(name = "CharacterStr")
    public int CharacterStr;

    @Column(name = "CharacterDex")
    public int CharacterDex;

    @Column(name = "CharacterCon")
    public int CharacterCon;

    @Column(name = "hitPoints")
    public int hitPoints;

    public int getCharacterLocation() {
        return characterLocation;
    }

    public void setCharacterLocation(int characterLocation) {
        this.characterLocation = characterLocation;
    }

    public int characterLocation;

//    @Basic
//    public List<String> inventory = new ArrayList<String>();
//    //List<String> arguments = new ArrayList<String>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public int getCharacterInt() {
        return CharacterInt;
    }

    public void setCharacterInt(int characterInt) {
        CharacterInt = characterInt;
    }

    public int getCharacterWis() {
        return CharacterWis;
    }

    public void setCharacterWis(int characterWis) {
        CharacterWis = characterWis;
    }

    public int getCharacterCha() {
        return CharacterCha;
    }

    public void setCharacterCha(int characterCha) {
        CharacterCha = characterCha;
    }

    public int getCharacterStr() {
        return CharacterStr;
    }

    public void setCharacterStr(int characterStr) {
        CharacterStr = characterStr;
    }

    public int getCharacterDex() {
        return CharacterDex;
    }

    public void setCharacterDex(int characterDex) {
        CharacterDex = characterDex;
    }

    public int getCharacterCon() {
        return CharacterCon;
    }

    public void setCharacterCon(int characterCon) {
        CharacterCon = characterCon;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

//    public List getInventory() {
//        return inventory;
//    }
//
//    public void setInventory(List inventory) {
//        this.inventory = inventory;
//    }









}
