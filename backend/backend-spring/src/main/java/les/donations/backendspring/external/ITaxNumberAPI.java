package les.donations.backendspring.external;

import java.io.IOException;

public interface ITaxNumberAPI {

    /**
     * Method that returns the email of a valid company by its tax number
     * @param taxNumber the tax number of the company
     * @return the email of the company
     */
    String getEmailByCompanyTaxNumber(String taxNumber) throws IOException;
}
