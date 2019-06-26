/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
*/

package bankatm2.pkg0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConnection {

    //in form http://localhost:1234
    String url;

    public ApiConnection(String _url){
        url = _url;
    }

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    public String checkPin(String acct, String pin) throws Exception{
        return getHTML(url + "/customer/" + acct + "/" + pin);
    }

    public String checkBalance(String acct, String pin) throws Exception{
        return getHTML(url + "/customer/" + acct + "/" + pin + "/balance");
    }


    //atm/ACCOUNTNUMBER/PIN/AMOUNT
    public String transact(String acct, String pin, Double amount) throws Exception{
        return getHTML(url + "/atm/" + acct + "/" + pin + "/" + String.valueOf(amount));
    }

}
