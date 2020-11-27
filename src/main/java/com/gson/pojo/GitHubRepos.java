package com.gson.pojo;

public class GitHubRepos {

    private Integer id;
    private String node_id;
    private String name;
    private String full_name;
    private String html_url;
    private String description;
    private Integer subscribers_count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSubscribers_count() {
        return subscribers_count;
    }

    public void setSubscribers_count(Integer subscribers_count) {
        this.subscribers_count = subscribers_count;
    }

    private Owner owner;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public static class Owner {
        private String login;
        private String avatar_url;
        private String url;
        private Boolean site_admin;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Boolean getSite_admin() {
            return site_admin;
        }

        public void setSite_admin(Boolean site_admin) {
            this.site_admin = site_admin;
        }
    }

    private Permissions permissions;

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public static class Permissions {
        private Boolean admin;
        private Boolean push;
        private Boolean pull;

        public Boolean getAdmin() {
            return admin;
        }

        public void setAdmin(Boolean admin) {
            this.admin = admin;
        }

        public Boolean getPush() {
            return push;
        }

        public void setPush(Boolean push) {
            this.push = push;
        }

        public Boolean getPull() {
            return pull;
        }

        public void setPull(Boolean pull) {
            this.pull = pull;
        }
    }
}
