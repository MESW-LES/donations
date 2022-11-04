package les.donations.backendspring.retrofit;

import java.io.IOException;
import java.net.MalformedURLException;

public interface ITaxNumberAPI {

    /**
     * Method that returns the email of a valid company by its tax number
     * @param taxNumber the tax number of the company
     * @return the email of the company
     */
    String getEmailByCompanyTaxNumber(String taxNumber) throws IOException;
}
