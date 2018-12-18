package com.bobocode.footballmanager.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode (of = "id")
@ToString
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Builder.Default
    @Setter ( AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "team")
    private List<Player> players = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "captain_id")
    private Player captain;

    public void addPlayer(Player player){
        players.add(player);
        player.setTeam(this);
    }

    public void addCaptain(Player player){
        captain = player;
    }
}
