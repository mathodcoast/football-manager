package com.bobocode.footballmanager.persistence;

import com.bobocode.footballmanager.model.Team;

import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByName(String name);
}
