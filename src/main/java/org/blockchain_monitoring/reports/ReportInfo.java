package org.blockchain_monitoring.reports;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ruslan Kryukov on 01/06/2017.
 */
public class ReportInfo {
    @JsonProperty("user_email")
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public String getDate() {
        return date;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    @JsonProperty("date")
    private String date;
    @JsonProperty("stacktrace")
    private String stackTrace;

    @JsonProperty("exception_name")
    private String exceptionName;

    @JsonProperty("exception_message")
    private String exceptionMessage;

    @JsonProperty("ip_address")
    private String ipAddress;

    ReportInfo(Exception e, String userEmail, String ip) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        this.stackTrace = stringWriter.toString();

        this.exceptionName = e.toString();
        this.exceptionMessage = e.getMessage();

        LocalDateTime t = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);

        this.date = t.format(formatter);
        this.userEmail = userEmail;
        this.ipAddress = ip;
    }
}
