package org.blockchain_monitoring.reports;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

/**
 * Created by Ruslan Kryukov on 01/06/2017.
 */
public class CrashReporter {
    private static String reportsURL;
    private static String userEmail;
    private static String userIP;
    private static boolean isReportsEnables;
    private static ObjectMapper mapper = new ObjectMapper(new JsonFactory());
    private static HttpClient httpClient = HttpClientBuilder.create().build();

    public static void wrap(AppBody app) {
        try {
            loadProps();
            app.run();

        } catch (Exception ex) {
            if(isReportsEnables) {
                ReportInfo reportInfo = new ReportInfo(ex, userEmail, userIP);
                try {
                    String json = mapper.writeValueAsString(reportInfo);
                    sendReport(json);
                } catch (Exception exx) {
                    exx.printStackTrace();
                }
            }
        }
    }

    private static void loadProps() {
        try (final InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("monitoring.properties")) {
            Properties properties = new Properties();
            properties.load(stream);
            reportsURL = properties.getProperty("reports.url");
            userEmail = System.getenv("USER_EMAIL");
            userEmail = userEmail != null ? userEmail : "not_provided@email.null";
            String reportsEnabled = System.getenv("REPORTS_ENABLED");
            isReportsEnables = reportsEnabled != null && reportsEnabled.toLowerCase().equals("true");
            try {
                userIP = IOUtils.toString(new URI("https://myexternalip.com/raw")).replace("\n", "");
            } catch (Exception ignore) {
                userIP = "127.0.0.1";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendReport(String report) throws Exception {
        HttpPost request = new HttpPost(reportsURL);
        StringEntity params = new StringEntity(report);
        request.addHeader("content-type", "application/x-www-form-urlencoded");
        request.addHeader("X-BLOCKCHAIN-MONITORING-APP", "Crash-Report");
        request.setEntity(params);

        HttpResponse response = httpClient.execute(request);

        if(response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failure when tried to send report!");
        }
    }
}
