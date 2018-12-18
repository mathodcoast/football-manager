package com.bobocode.footballmanager.web.controller;

import com.bobocode.footballmanager.model.Player;
import com.bobocode.footballmanager.model.Team;
import com.bobocode.footballmanager.model.dto.PlayerDto;
import com.bobocode.footballmanager.model.dto.TeamDto;
import com.bobocode.footballmanager.service.FootballManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping

public class FootballManagerController {
    private FootballManagerService footballManagerService;

    @Autowired
    public FootballManagerController(FootballManagerService footballManagerService) {
        this.footballManagerService = footballManagerService;
    }

    @GetMapping
    public String hello(){
        return "Hello";
    }

    @PostMapping("/players")
    public void savePlayer(@RequestBody Player player){
        footballManagerService.savePlayer(player);
    }

    @GetMapping("/players/{id}")
    public PlayerDto getPlayer(@PathVariable Long id){
        return footballManagerService.getPlayerById(id);
    }

    @PutMapping("/players/{id}")
    public void updatePlayer(@PathVariable Long id,@RequestBody Player player){
        player.setId(id);
        footballManagerService.savePlayer(player);
    }

    @GetMapping("/players")
    public List<PlayerDto> getAllPlayers(){
        return footballManagerService.getAllPlayersList();
    }

    @GetMapping("/players/team/{id}")
    public List<PlayerDto> getPlayersByTeam(@PathVariable Long id){
        return footballManagerService.getAllPlayersByTeam(id);
    }

    @DeleteMapping("/players/{id}")
    public void deletePlayer(@PathVariable Long id){
        footballManagerService.deletePlayer(id);
    }

    @PostMapping("/players/list")
    public void savePlayersList(@RequestBody List<Player> players){
        footballManagerService.savePlayers(players);
    }

    @PostMapping("/teams")
    public void saveTeam(@RequestBody Team team){
        footballManagerService.saveTeam(team);
    }

    @PostMapping("/teams/list")
    public void saveTeamsList(@RequestBody List<Team> teamList){
        footballManagerService.saveTeamsList(teamList);
    }

    @GetMapping("/teams/{id}")
    public TeamDto getTeam(@PathVariable Long id){
        return footballManagerService.getTeamById(id);
    }

    @GetMapping("/teams")
    public List<TeamDto> getTeamsList(){
        return footballManagerService.getAllTeamsList();
    }

    @PutMapping("/teams/{id}")
    public void updateTeam(@PathVariable Long id,@RequestBody Team team){
        team.setId(id);
        footballManagerService.saveTeam(team);
    }

    @DeleteMapping("/teams/{id}")
    public void deleteTeam(@PathVariable Long id){
        footballManagerService.deleteTeam(id);
    }

    @PostMapping("/teams/{teamId}")
    public void addNewPlayerToTeam(@RequestBody Player player,@PathVariable Long teamId){
        footballManagerService.addNewPlayerToTeam(player,teamId);
    }

    @PutMapping("/teams/{teamId}/{playerId}")
    public void addPlayerToTeam(@PathVariable Long teamId,@PathVariable Long playerId){
        footballManagerService.addPlayerToTeam(teamId, playerId);
    }

    @PutMapping("/teams/{teamId}/captain/{playerId}")
    public void setCaptain(@PathVariable Long teamId,@PathVariable Long playerId){
        footballManagerService.setCaptain(teamId,playerId);
    }

    @GetMapping("/teams/{teamId}/captain")
    public PlayerDto getCaptain(@PathVariable Long teamId){
        return footballManagerService.getCaptainOfTeam(teamId);
    }

}
