package com.bobocode.footballmanager.service.Impl;

import com.bobocode.footballmanager.exception.EntityNotFoundException;
import com.bobocode.footballmanager.model.Player;
import com.bobocode.footballmanager.model.Team;
import com.bobocode.footballmanager.model.dto.PlayerDto;
import com.bobocode.footballmanager.model.dto.TeamDto;
import com.bobocode.footballmanager.persistence.PlayerRepository;
import com.bobocode.footballmanager.persistence.TeamRepository;
import com.bobocode.footballmanager.service.FootballManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FootballManagerServiceImpl implements FootballManagerService {

    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;

    public FootballManagerServiceImpl(PlayerRepository playerRepository,TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    @Override
    public PlayerDto getPlayerById(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Player entity not found by id" + id));
        return getPlayerDto(player);
    }

    @Override
    public void deletePlayer(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Player entity not found by id" + playerId));
        playerRepository.delete(player);
    }

    @Override
    public List<PlayerDto> getAllPlayersList() {
        List<Player> playerList = (List<Player>) playerRepository.findAll();
        return getPlayerDtoList(playerList);
    }

    @Override
    public List<PlayerDto> getAllPlayersByTeam(Long id) {
        List<Player> playerList = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team entity not found by id" + id)).getPlayers();
        return getPlayerDtoList(playerList);
    }

    @Override
    public void savePlayers(List<Player> players) {
        playerRepository.saveAll(players);
    }

    @Override
    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void saveTeamsList(List<Team> teamList) {
        teamRepository.saveAll(teamList);
    }

    @Override
    public TeamDto getTeamById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Team entity not found by id" + id));
        return getTeamDto(team);
    }

    @Override
    public void deleteTeam(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Team entity not found by id" + teamId));
        teamRepository.delete(team);
    }

    @Override
    public List<TeamDto> getAllTeamsList() {
        List<Team> teamList = (List<Team>) teamRepository.findAll();
        return teamList.stream().map(this::getTeamDto).collect(Collectors.toList());
    }

    @Override
    public void addNewPlayerToTeam(Player player, Long teamId) {
        playerRepository.save(player);
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Team entity not found by id" + teamId));
        team.addPlayer(player);
    }

    @Override
    public void addPlayerToTeam(Long teamId, Long playerId){
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Team entity not found by id" + teamId));
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new EntityNotFoundException("Player entity not found by id" + playerId));
        team.addPlayer(player);
    }

    @Override
    public void setCaptain(Long teamId,Long playerId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Team entity not found by id" + teamId));
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new EntityNotFoundException("Player entity not found by id" + playerId));
        if(team.getPlayers().contains(player)){
            team.addCaptain(player);
        }else throw new javax.persistence.EntityNotFoundException("This player is not in team composition. It can't become a captain.");
    }

    @Override
    public PlayerDto getCaptainOfTeam(Long id) {
        Player player = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team entity not found by id" + id)).getCaptain();
        return getPlayerDto(player);
    }

    private PlayerDto getPlayerDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());
        playerDto.setFirstName(player.getFirstName());
        playerDto.setLastName(player.getLastName());
        playerDto.setBirthday(player.getBirthday());
        if(player.getTeam() != null) {
            playerDto.setTeamName(player.getTeam().getName());
        } else {playerDto.setTeamName("No team");}
        return playerDto;
    }

    private List<PlayerDto> getPlayerDtoList(List<Player> playerList) {
        return playerList.stream().map(this::getPlayerDto).collect(Collectors.toList());
    }

    private TeamDto getTeamDto(Team team) {
        TeamDto teamDto = new TeamDto();
        teamDto.setId(team.getId());
        teamDto.setName(team.getName());
        teamDto.setCity(team.getCity());
        if(team.getCaptain() != null){
            teamDto.setCaptainName(team.getCaptain().getFirstName() + " " + team.getCaptain().getLastName());
        }
        return teamDto;
    }
}
