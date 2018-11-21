package com.galvanize.gamecharacter.service;

import com.galvanize.gamecharacter.model.GameCharacter;
import com.galvanize.gamecharacter.repository.CharacterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Service
public class CharacterService  {


    private final CharacterRepository characterRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterService.class);

    @Autowired
    public CharacterService(CharacterRepository repository){
        this.characterRepository = repository;
    }



    /**
     * Generate random number array for given limits.
     */

    public int[] generateRandomInt(int min, int max, int arrayLength){
        int [] array = new int[arrayLength];
        Random random = new Random();

        for(int k = 0; k<6; k++) {
            int r = (int) (Math.random() * (min - max)) + max; //random.nextInt(max + 1 - min) + min;
            array[k] = r;
        }
        LOGGER.info("Random Array : " + array.length);
        return array;
    }

    /** remove Min and max from array */
    public int[] removeElement(int intArr[], int minVal, int maxVal){
        // remove maxVal
            for (int i = 0; i < intArr.length; i++) {
                if (intArr[i] == maxVal) {
                    // shifting elements
                    for (int j = i; j < intArr.length - 1; j++) {
                        intArr[j] = intArr[j + 1];
                    }
                    break;
                }
            }
            //remove minVal
            for (int i = 0; i < intArr.length; i++) {
            if (intArr[i] == minVal) {
                // shifting elements
                for (int j = i; j < intArr.length - 1; j++) {
                    intArr[j] = intArr[j + 1];
                }
                break;
                }
            }

            LOGGER.info("Removing Elements from array, intArr : " + intArr.length);


        LOGGER.info("Remove Max and Min from Array : " + intArr.length);
        return intArr;
    }

    /** Assigning values to GameCharacter **/
    public GameCharacter setCharacter(GameCharacter character, int randomNum[]) {

        /**
         * If class = Warrior highest number should be assigned to Str and the lowest to Int,
         * if class = Archer highest number goes to Dex and lowested to Cha,
         * if class = Wizard highest number goes to Int and the lowest to Str,
         * if class = Rogue  highest goes to Cha and the lowest goes to Str.
         * int, wis, cha, str, dex, con
         */

        //find min and max, assign then remove from array.
        // assign other 4 numbers to different fields in character.

        int maxNum = randomNum[0];
        int minNum = randomNum[0];
        for (int i = 1; i < randomNum.length; i++) {
            if ( randomNum[i] > maxNum) {
                maxNum = randomNum[i];
            }
            if ( randomNum[i] < minNum){
                minNum = randomNum[i];
            }
        }

        //int, wis, cha, str, dex, con
        String className = character.getCharacterClass();
        if(className.equals("Warrior")) {
            character.setCharacterStr(maxNum);
            character.setCharacterInt(minNum);
            randomNum = removeElement(randomNum, maxNum, minNum);
            // assign other numbers to Character variables.
            character.setCharacterWis(randomNum[0]);
            character.setCharacterCha(randomNum[1]);
            character.setCharacterDex(randomNum[2]);
            character.setCharacterCon(randomNum[3]);
        }
        else if(className.equals("Archer")){
            character.setCharacterDex(maxNum);
            character.setCharacterCha(minNum);
            randomNum = removeElement(randomNum, maxNum, minNum);
            // assign other numbers to Character variables.
            character.setCharacterInt(randomNum[0]);
            character.setCharacterWis(randomNum[1]);
            character.setCharacterStr(randomNum[2]);
            character.setCharacterCon(randomNum[3]);

        }
        else if(className.equals("Wizard")){
            character.setCharacterInt(maxNum);
            character.setCharacterStr(minNum);
            randomNum = removeElement(randomNum, maxNum, minNum);
            // assign other numbers to Character variables.
            character.setCharacterCha(randomNum[0]);
            character.setCharacterWis(randomNum[1]);
            character.setCharacterDex(randomNum[2]);
            character.setCharacterCon(randomNum[3]);
        }
        else if(className.equals("Rogue")){
            character.setCharacterCha(maxNum);
            character.setCharacterStr(minNum);
            randomNum = removeElement(randomNum, maxNum, minNum);
            // assign other numbers to Character variables.
            character.setCharacterInt(randomNum[0]);
            character.setCharacterWis(randomNum[1]);
            character.setCharacterDex(randomNum[2]);
            character.setCharacterCon(randomNum[3]);
        }
        character.setHitPoints(randomNum[3] * 2);
        character.setCharacterLocation(4);

        LOGGER.info("Before adding to DB GameCharacter :" + character.getCharacterName());
        return character;
    }



    public ResponseEntity<Object> addCharacter(GameCharacter character, String name, String classname){

        // generate rendome values and assign to character object
        int [] redomArray  = generateRandomInt(8, 18, 6);
        character.setCharacterName(name);
        character.setCharacterClass(classname);
        setCharacter(character, redomArray);
        this.characterRepository.save(character);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public Iterable<GameCharacter> getAllCharacterByClass(String classname){


        Iterable<GameCharacter> allChars = this.characterRepository.findGameCharacterByClassName(classname);
        if(allChars == null){
            ResponseEntity.notFound();
        }

            return allChars;
    }

}
