package les.donations.backendspring.repository.administrator;

import les.donations.backendspring.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorDao extends JpaRepository<Administrator, Long> {

    /**
     * Method that verifies if a valid administrator exists in the system with a specific email
     * @param email the administrator email
     * @param active a boolean that gets the active admins
     * @return a boolean that declares if the admin exists
     */
    boolean existsAdministratorByEmailAndActive(String email, boolean active);
}
