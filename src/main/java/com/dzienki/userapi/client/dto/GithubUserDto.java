package com.dzienki.userapi.client.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubUserDto {

    private String login;
    private int id;
    private String node_id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String followers_url;
    private String following_url;
    private String gists_url;
    private String starred_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private boolean site_admin;
    private String name;
    private String company;
    private String blog;
    private String location;
    private Object email;
    private Object hireable;
    private Object bio;
    private Object twitter_username;
    private long public_repos;
    private long public_gists;
    private long followers;
    private long following;
    private Date created_at;
    private Date updated_at;
}
