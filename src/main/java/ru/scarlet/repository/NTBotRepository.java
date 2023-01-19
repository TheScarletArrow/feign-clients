package ru.scarlet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.scarlet.entity.NTBot;

public interface NTBotRepository extends JpaRepository<NTBot,Integer> {
    boolean existsByTgId(String tgId);

    @Query("select n from NTBot n where n.tgId= :tgId")
    NTBot findByTgId(String tgId);

}
