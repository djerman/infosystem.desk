package rs.atekom.infosystem.desk;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.application.Application;

@SpringBootApplication
public class AtekomDeskApplication {

	public static void main(String[] args) {
		//SpringApplication.run(RmdeskApplication.class, args);
		Application.launch(AtekomApp.class, args);
		}
	}
