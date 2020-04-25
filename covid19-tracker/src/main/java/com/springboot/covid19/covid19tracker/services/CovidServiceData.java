package com.springboot.covid19.covid19tracker.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.springboot.covid19.covid19tracker.models.LocationStats;

@Service
public class CovidServiceData {
	public static String covid_Service_String_URL = "https://raw.githubusercontent.com"
			+ "/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<LocationStats> allStats = new ArrayList<LocationStats>();
	

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchCovidData() throws IOException{
		URL covid_url = new URL(covid_Service_String_URL);
		List<LocationStats> newStats = new ArrayList<LocationStats>();
		HttpURLConnection con = (HttpURLConnection) covid_url.openConnection(); 
		con.setRequestMethod("GET");
		String httpRawData = new String();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); 
		String inputLine; 
		while ((inputLine = in.readLine()) != null) {
			httpRawData += "\n"+inputLine;
		}
		in.close();
		
		//System.out.println(httpRawData);
		StringReader sr = new StringReader(httpRawData);
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(sr);
		for (CSVRecord record : records) {
			LocationStats ls = new LocationStats();
			ls.setState(record.get("Province/State"));
			ls.setCountry(record.get("Country/Region"));
			int latestCases = Integer.parseInt(record.get(record.size()-1));
			int diffPrevDay = Integer.parseInt(record.get(record.size()-2));
			ls.setLatestTotalCases(latestCases);
			ls.setDiffCasesPrevDay(latestCases- diffPrevDay);
			newStats.add(ls);
		}
		this.allStats = newStats; 
	}
	
	public List<LocationStats> getAllStats() {
		return allStats;
	}
}
