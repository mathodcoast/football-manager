package com.bobocode.footballmanager.service;

import com.bobocode.footballmanager.model.Player;
import com.bobocode.footballmanager.model.Team;
import com.bobocode.footballmanager.model.dto.PlayerDto;
import com.bobocode.footballmanager.model.dto.TeamDto;

import java.util.List;


public interface FootballManagerService {

    void savePlayer(Player team);

    PlayerDto getPlayerById(Long id);

    void deletePlayer(Long playerId);

    List<PlayerDto> getAllPlayersList();

    List<PlayerDto> getAllPlayersByTeam(Long id);

    void savePlayers (List<Player> players);

    void saveTeam(Team team);

    void saveTeamsList(List<Team> teamList);

    TeamDto getTeamById(Long id);

    void deleteTeam(Long id);

    List<TeamDto> getAllTeamsList();

    void addNewPlayerToTeam(Player player,Long teamId);

    void addPlayerToTeam(Long playerId, Long teamId);

    void setCaptain(Long teamId, Long playerId);

    PlayerDto getCaptainOfTeam(Long id);
}
