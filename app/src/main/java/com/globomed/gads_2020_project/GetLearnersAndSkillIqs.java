package com.globomed.gads_2020_project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetLearnersAndSkillIqs {

    @GET("/api/hours")
    Call<List<DataModelLearners>> getLearners();
    @GET("/api/skilliq")
    Call<List<DataModelSkill>> getSkillIqs();
}