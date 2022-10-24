package les.donations.backendspring;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        Environment environment = Environment.getInstance();
        String envType = environment.getEnvType();
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(environment.getEnv(envType + "_DATABASE"));
        dataSourceBuilder.username(environment.getEnv( "USER_DATABASE"));
        dataSourceBuilder.password(environment.getEnv("PWD_DATABASE"));
        // deletes the content from the test environment
        DataSource dataSource = dataSourceBuilder.build();
        truncateTablesForTestEnvironment(dataSource, envType);
        return dataSource;
    }

    /**
     * Method that clears all the data tables content to have a clean test environment
     * @param dataSource the data source that connects to the database
     * @param envType the type of environment that the application is running
     */
    private void truncateTablesForTestEnvironment(DataSource dataSource, String envType)  {
        try{
            // if it is running the environment test then deletes everything from the database
            if(envType.equals(Environment.TEST_ENV)) {
                StringBuilder tablesBuilder = new StringBuilder(DatabaseConstant.TRUNCATE);
                Arrays.asList(DatabaseConstant.TABLES).forEach(table -> tablesBuilder.append(table).append(","));
                // gets the truncate tables of the database
                String tables = tablesBuilder.toString();
                dataSource.getConnection().prepareStatement(tables.substring(0, tables.length() - 1)).execute();
            }
        }catch (SQLException e) {
            System.out.println("The data tables don't exist! It's Ok!");
        }
    }
}
