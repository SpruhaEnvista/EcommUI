package com.envista.msi.api.web.rest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * This utility class is specially designed to keep all methods which are commonly used/to be used over different module in Avatar project.
 *
 * Created by Sujit kumar on 21/06/2017.
 */
public class CommonUtil {

    /**
     * Used to get response in the form of string by connecting to the server through given URL and request payload.
     * This is basically a POST call to the server, requestXML is the request payload in XML format.
     *
     * On exception, it returns an empty string.
     *
     * @param urlString
     * @param requestXML
     * @return
     * @throws Exception
     */
    public static String connectAndGetResponseAsString(String urlString, String requestXML) {

        StringBuffer respStr = new StringBuffer();
        BufferedReader in = null;
        OutputStreamWriter out = null;
        try {
            URL url = new URL(urlString);

            URLConnection connection;
            connection = url.openConnection();
            connection.setRequestProperty("Content-Type", "application/*+xml");
            connection.setDoOutput(true);
            out = new OutputStreamWriter(connection.getOutputStream(), Charset.forName("UTF-8"));
            out.write(requestXML.trim());
            out.close();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String resp = "";
            while ((resp = in.readLine()) != null) {
                respStr.append(resp);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respStr.toString();
    }
}
