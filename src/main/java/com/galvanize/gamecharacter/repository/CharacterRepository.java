package com.galvanize.gamecharacter.repository;
import com.galvanize.gamecharacter.model.GameCharacter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends CrudRepository<GameCharacter, Long> {

    @Query("from GameCharacter gc where gc.characterClass=:classname")
    public List<GameCharacter> findGameCharacterByClassName(@Param("classname") String name);

}
