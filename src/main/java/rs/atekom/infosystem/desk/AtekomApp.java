package rs.atekom.infosystem.desk;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;

/**
 * JavaFX ATekom Desktop App
 */
@EntityScan(basePackages = "rs.atekom.infosystem.baza")
public class AtekomApp extends Application {
	
	private ConfigurableApplicationContext context;
	
	@Override
	public void init() throws Exception {
        ApplicationContextInitializer<GenericApplicationContext> initializer = new ApplicationContextInitializer<GenericApplicationContext>() {
            @Override
            public void initialize(GenericApplicationContext genericApplicationContext) {
                genericApplicationContext.registerBean(Application.class, () -> AtekomApp.this);
                genericApplicationContext.registerBean(Parameters.class, () -> getParameters());
                genericApplicationContext.registerBean(HostServices.class, () -> getHostServices());
                }
            };
			
		this.context = new SpringApplicationBuilder()
				.sources(AtekomDeskApplication.class)
				.initializers(initializer)
				.run(getParameters().getRaw().toArray(new String[0]));	
		}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.context.publishEvent(new StageReadyEvent(primaryStage));
		primaryStage.setResizable(false);
		}
	
	@Override
	public void stop() {
		this.context.close();
		try {
			super.stop();
			} catch (Exception e) {
				e.printStackTrace();
				}
		}
	
	class StageReadyEvent extends ApplicationEvent {

		private static final long serialVersionUID = 1L;

		public Stage getStage() {
			return Stage.class.cast(getSource());
			}
		
		public StageReadyEvent (Stage source) {
			super(source);
			}
		}
	
	/*
    public static void initSecurity() {
        SecurityContextHolder.setStrategyName("MODE_GLOBAL");
        initAnonymous();
    }
    
    public static void initAnonymous() {
        AnonymousAuthenticationToken auth = new AnonymousAuthenticationToken(
                "anonymous", "anonymous",
                AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public static void logout(){
        SecurityContextHolder.clearContext();
        initAnonymous();
    }**/
	

	}