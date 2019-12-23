package com.redmineTask;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.TimeEntry;

public class TimeEntrysController {
	
	Gson returnGsonWithPrettyPrinting() {
		GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson;
	}
	
    public String getTimeEntrysInProject(RedmineManager manager, LocalDate inputDate) {
        final Map<String, String> parameters = new HashMap<String, String>();
        String jsonStringTimeEntrys = null;
        ArrayList<TimeEntryData> dataset = new ArrayList<TimeEntryData>();
        TimeEntryData tmp = null;
        List<TimeEntry> timeEntries;
        Gson gson = returnGsonWithPrettyPrinting();
        Date dateToConvert = null;
        
        LocalDate covertedDate = null;
        
        try {
            timeEntries = manager.getTimeEntryManager().getTimeEntries(parameters).getResults();
            for (int i = 0; i < timeEntries.size(); i++) {
            	dateToConvert = timeEntries.get(i).getSpentOn();
            	covertedDate = dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            	
            	if(inputDate.equals(covertedDate) && timeEntries.get(i).getHours()==0) {
	                tmp = new TimeEntryData();
	                tmp.hours = timeEntries.get(i).getHours();
	                tmp.user = timeEntries.get(i).getUserName();
	                dataset.add(tmp);
            	}
            	
            	
            }
            jsonStringTimeEntrys = gson.toJson(dataset);
        }
        catch(RedmineException e) {
            System.out.println("RedmineException in TimeEntrysController Class.");
            e.printStackTrace();
        }
        
        return jsonStringTimeEntrys;
    }
}
