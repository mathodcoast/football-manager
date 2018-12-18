package com.bobocode.footballmanager.persistence;

import com.bobocode.footballmanager.model.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {

}
