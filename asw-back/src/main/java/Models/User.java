package Models;

public class User {
    private String username;
    private String created;
    private Integer karma;
    private String about;

    public User(String username, String created, Integer karma, String about){
        setUsername(username);
        setCreated(created);
        setKarma(karma);
        setAbout(about);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getKarma() {
        return karma;
    }

    public void setKarma(Integer karma) {
        this.karma = karma;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
