package com.example.demo.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column
    private String username;
    @Column
    private Date created;
    @Column
    private int karma;
    @Column
    private String about;
    @Column
    private int maxvisit;
    @Column
    private int minaway;
    @Column
    private int delay;
    @Column
    private boolean showdead;
    @Column
    private boolean noprocrast;
    @Column
    private String apiKey;
    @Column
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public int getMaxvisit() {
        return maxvisit;
    }

    public void setMaxvisit(int maxvisit) {
        this.maxvisit = maxvisit;
    }

    public int getMinaway() {
        return minaway;
    }

    public void setMinaway(int minaway) {
        this.minaway = minaway;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isShowdead() {
        return showdead;
    }

    public void setShowdead(boolean showdead) {
        this.showdead = showdead;
    }

    public boolean isNoprocrast() {
        return noprocrast;
    }

    public void setNoprocrast(boolean noprocrast) {
        this.noprocrast = noprocrast;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
