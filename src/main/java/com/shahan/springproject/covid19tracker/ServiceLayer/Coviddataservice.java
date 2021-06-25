package com.shahan.springproject.covid19tracker.ServiceLayer;

import com.shahan.springproject.covid19tracker.DAO.Locationstats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class Coviddataservice {
    private static String URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<Locationstats> allstats= new ArrayList<>();

    public List<Locationstats> getAllstats() {
        return allstats;
    }

    //initialising url to a string variable for HTTP request and HTTP responce
    @PostConstruct // tells spring to execute after creating instance of the class
    public void fetchData() throws IOException,InterruptedException {

        HttpClient client= HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder().uri(URI.create(URL)).build(); // uri.create creates a uri of the string that compiler understands
        HttpResponse<String> httpResponse= client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvReader= new StringReader(httpResponse.body());
        //Prasing the responce using open source library Apache CSV
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
         List<Locationstats> newstats= new ArrayList<>(); // avoid concurrency
        for (CSVRecord record : records) {
            Locationstats locationstat= new Locationstats();
            locationstat.setState(record.get("Province/State"));
            locationstat.setCountry(record.get("Country/Region"));
            locationstat.setTotal(Integer.parseInt(record.get(record.size()-1)));
            newstats.add(locationstat);
        }
        this.allstats=newstats;
    }
}
