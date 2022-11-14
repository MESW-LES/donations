package les.donations.backendspring.external;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class TaxNumberAPI implements ITaxNumberAPI{

    private final static String URL = "http://www.nif.pt/?json=1&q=taxNumber&key=key";

    @Override
    public String getEmailByCompanyTaxNumber(String taxNumber) throws IOException {
        String companyEmail;
        String urlStr = URL;
        urlStr = urlStr.replaceAll("taxNumber", taxNumber).replaceAll("key", "5dc44193a6cb1c4b01bc1eccde723ad4");
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            try{
                // makes the request
                connection.setRequestMethod("GET");
                JSONObject jsonObject = (JSONObject) new JSONParser().parse(new InputStreamReader(connection.getInputStream()));
                System.out.println(jsonObject.toString());
                JSONObject companyInfo = (JSONObject) ((JSONObject) jsonObject.get("records")).get(taxNumber);

                // if its a not a valid company (does not have CAE)
                String companyCAE = companyInfo.get("cae") != null ? companyInfo.get("cae").toString() : null;
                if(companyCAE == null){
                    return null;
                }

                // gets the company email
                JSONObject companyContacts = (JSONObject) companyInfo.get("contacts");
                companyEmail = companyContacts.get("email") != null ? companyContacts.get("email").toString() : null;
            }finally {
                connection.disconnect();
            }
        } catch (IOException | ParseException e) {
            throw new IOException("It is impossible to check if the tax number is valid!");
        }
        return companyEmail;
    }
}
