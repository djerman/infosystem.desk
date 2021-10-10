package rs.atekom.infosystem.desk;

import java.io.IOException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rs.atekom.infosystem.desk.AtekomApp.StageReadyEvent;

@Component
public class StageListener implements ApplicationListener<StageReadyEvent>{

	private final String nazivAplikacije;
	private final Resource fxml;
	private final ApplicationContext ac;
	
	public StageListener(@Value("${spring.application.ui.title}") String naziv, @Value("classpath:/Prijava.fxml") Resource reource, ApplicationContext ac) {
		this.nazivAplikacije = naziv;
		this.fxml = reource;
		this.ac = ac;
		}
	
	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		try {
			Stage stage = event.getStage();
		    URL url = this.fxml.getURL();
			FXMLLoader fxmlloader = new FXMLLoader(url);
			fxmlloader.setControllerFactory(ac::getBean);
			Parent root = fxmlloader.load();
			Scene scene = new Scene(root, 330, 120);
			stage.setScene(scene);
			stage.setTitle(this.nazivAplikacije);
			stage.show();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());//e.printStackTrace();
				}
		}
	}
