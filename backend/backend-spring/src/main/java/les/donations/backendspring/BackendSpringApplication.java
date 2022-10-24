package les.donations.backendspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendSpringApplication {

	public static void main(String[] args) {
		// in case the application starts running without environment flag
		if(args.length == 0){
			throw new IllegalArgumentException("The environment flag is not defined!");
		}
		Environment environment = Environment.getInstance();
		environment.setEnvType(args[0]);
		SpringApplication.run(BackendSpringApplication.class, args);
	}

}
