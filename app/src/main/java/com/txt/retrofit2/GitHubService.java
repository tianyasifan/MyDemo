package com.txt.retrofit2;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by txt on 2016/6/3.
 */
public interface GitHubService {
    @GET("/users/{user}/repos")
    List<Repo> listRepos(@Path("user") String user);
}
