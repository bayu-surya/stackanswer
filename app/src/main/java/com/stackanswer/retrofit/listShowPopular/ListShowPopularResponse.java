package com.stackanswer.retrofit.listShowPopular;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListShowPopularResponse{

	@SerializedName("page")
	private int page;

	@SerializedName("results")
	private List<ResultsItem> results;

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	@NotNull
	@Override
 	public String toString(){
		return 
			"ListShowPopularResponse{" + 
			"page = '" + page + '\'' + 
			",results = '" + results + '\'' + 
			"}";
		}
}