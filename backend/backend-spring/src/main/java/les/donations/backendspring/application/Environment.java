package les.donations.backendspring.application;

import io.github.cdimascio.dotenv.Dotenv;

public class Environment {

    protected final static String DEV_ENV = "DEV";
    protected final static String PROD_ENV = "PROD";
    protected final static String TEST_ENV = "TEST";

    private static Environment environment;
    private Dotenv dotenv;
    private String envType;

    public static Environment getInstance(){
        if(environment == null){
            environment = new Environment();
        }
        return environment;
    }

    private Environment(){
        dotenv = Dotenv.configure().filename(".env")
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
    }

    public String getEnv(String property){
        return dotenv.get(property);
    }

    protected String getEnvType() {
        return envType;
    }

    protected void setEnvType(String envType){
        // removes the '-' from the command line argument
        this.envType = envType.substring(1).toUpperCase();
    }
}
