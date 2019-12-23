package com.redmineTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.Issue;

public class IssuesController {

	Gson returnGsonWithPrettyPrinting() {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		return gson;
	}

	public String getIssuesInProject(RedmineManager manager, String projectKey) {
		final Map<String, String> parameters = new HashMap<String, String>();
		String jsonStringIssues = null;
		ArrayList<IssueData> dataset = new ArrayList<IssueData>();
		IssueData tmp;
		List<Issue> issues;
		Gson gson = returnGsonWithPrettyPrinting();

		try {
			issues = manager.getIssueManager().getIssues(parameters).getResults();
			for (int i = 0; i < issues.size(); i++) {
				if (issues.get(i).getProjectName().equalsIgnoreCase(projectKey)) {
					tmp = new IssueData();
					tmp.assignee = issues.get(i).getAssigneeName();
					tmp.subject = issues.get(i).getSubject();
					tmp.status = issues.get(i).getStatusName();
					dataset.add(tmp);
				}
			}
			jsonStringIssues = gson.toJson(dataset);
		} catch (RedmineException e) {
			System.out.println("RedmineException in IssuesController Class.");
			e.printStackTrace();
		}

		return jsonStringIssues;
	}
}
