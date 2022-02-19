package com.example.demo;

import com.example.demo.player.Player;
import com.example.demo.player.PlayerRepository;
import com.example.demo.stadium.Stadium;
import com.example.demo.stadium.StadiumRepository;
import com.example.demo.team.Team;
import com.example.demo.team.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(
			PlayerRepository playerRepository,
			TeamRepository teamRepository,
			StadiumRepository stadiumRepository,
			MongoTemplate mongoTemplate) {
		return args -> {

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 1987);
			calendar.set(Calendar.MONTH, Calendar.JUNE);
			calendar.set(Calendar.DAY_OF_MONTH, 24);

			Date dateOfBirth = calendar.getTime();

			String pesel = "00321112234";
			String teamName = "Paris Saint Germain";
			String stadiumName = "Parc des Princes";

			Stadium stadium = new Stadium(
					stadiumName,
					47929,
					"Yes",
					"Yes"
			);
			Team team = new Team(
					teamName,
					"Paris",
					stadiumName
			);
			Player player = new Player(
					pesel,
					"Lionel",
					"Messi",
					30000000,
					dateOfBirth,
					"player",
					"forward",
					teamName
			);

//			usingMongoTemplateAndQuery(repository, mongoTemplate, pesel, player);

			playerRepository.findPlayerByPesel(pesel)
					.ifPresentOrElse(s -> {
						System.out.println(s + " already exists");
					}, () -> {
						System.out.println("Inserting player " + player);
						playerRepository.insert(player);
					});

			teamRepository.findTeamByName(teamName)
					.ifPresentOrElse(s -> {
						System.out.println(s + " already exists");
					}, () -> {
						System.out.println("Inserting team " + team);
						teamRepository.insert(team);
					});

			stadiumRepository.findStadiumByName(stadiumName)
					.ifPresentOrElse(s -> {
						System.out.println(s + " already exists");
					}, () -> {
						System.out.println("Inserting stadium " + stadium);
						stadiumRepository.insert(stadium);
					});
		};
	}

	private void usingMongoTemplateAndQuery(PlayerRepository repository, MongoTemplate mongoTemplate, String pesel, Player player) {
		Query query = new Query();
		query.addCriteria(Criteria.where("pesel").is(pesel));

		List<Player> players = mongoTemplate.find(query, Player.class);

		if (players.size() > 1) {
			throw new IllegalStateException(
					"Found many players with pesel " + pesel
			);
		}

		if (players.isEmpty()) {
			System.out.println("Inserting player " + player);
			repository.insert(player);
		} else {
			System.out.println(player + " already exists");
		}
	}

}
