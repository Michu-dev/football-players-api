package com.example.demo;

import com.example.demo.player.Player;
import com.example.demo.player.PlayerRepository;
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
			PlayerRepository repository, MongoTemplate mongoTemplate) {
		return args -> {

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 1987);
			calendar.set(Calendar.MONTH, Calendar.JUNE);
			calendar.set(Calendar.DAY_OF_MONTH, 24);

			Date dateOfBirth = calendar.getTime();

			String pesel = "00321112234";

			Stadium stadium = new Stadium(
					"Parc des Princes",
					47929,
					"Yes",
					"Yes"
			);
			Team team = new Team(
					"Paris Saint Germain",
					"Paris",
					stadium
			);
			Player player = new Player(
					pesel,
					"Lionel",
					"Messi",
					30000000,
					dateOfBirth,
					"player",
					"forward",
					team
			);

//			usingMongoTemplateAndQuery(repository, mongoTemplate, pesel, player);

			repository.findPlayerByPesel(pesel)
					.ifPresentOrElse(s -> {
						System.out.println(s + " already exists");
					}, () -> {
						System.out.println("Inserting player " + player);
						repository.insert(player);
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
