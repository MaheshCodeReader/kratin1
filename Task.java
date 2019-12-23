package com.redmineTask;

import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.TimeEntry;
import com.taskadapter.redmineapi.internal.Transport;

public class Task {
	public static void main(String args[]) {
		String uri = "https://pivotal.truliacare.com/";
		String apiAccessKey = "b06446911d9e55d2fba1bdb50819c400d50b98b8";
		String projectKey = "sandbox";
		String jsonStringIssues;
		String jsonStringTimeEntries;
		LocalDate date = LocalDate.of(2019, 12, 5);
		
		IssuesController issuesController = new IssuesController();
		TimeEntrysController timeEntriesController = new TimeEntrysController();
		RedmineManager manager = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
	
		jsonStringIssues = issuesController.getIssuesInProject(manager, projectKey);
		System.out.println(jsonStringIssues);
		
		jsonStringTimeEntries = timeEntriesController.getTimeEntrysInProject(manager, date);
		System.out.println(jsonStringTimeEntries);
	}
}
