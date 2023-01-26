/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.AsaSpring.models;

import java.io.Serializable;

/**
 *
 * @author Marcos
 */

public class Movie implements Serializable {
    
private String Poster_Link;
private String Series_Title;
private String Released_Year;
private String Certificate;
private String Rumtime;
private String Genre;
private String IMDB_Rating;
private String Overview;
private String Meta_score;
private String Director;
private String Star1;
private String Star2;
private String Star3;
private String Star4;
private String Star5;
private String No_of_Votes;
private String Gross;
private String Rate;

private Integer id;
private String TMDBId;
private String Elenco;

    public Movie() {
        super();
    }

    public Movie(String Poster_Link, String Series_Title, String Released_Year, String Certificate, String Rumtime, String Genre, String IMDB_Rating, String Overview, String Meta_score, String Director, String Star1, String Star2, String Star3, String Star4, String Star5, String No_of_Votes, String Gross, String Rate, int id, String TMDBId, String Elenco) {
        this.Poster_Link = Poster_Link;
        this.Series_Title = Series_Title;
        this.Released_Year = Released_Year;
        this.Certificate = Certificate;
        this.Rumtime = Rumtime;
        this.Genre = Genre;
        this.IMDB_Rating = IMDB_Rating;
        this.Overview = Overview;
        this.Meta_score = Meta_score;
        this.Director = Director;
        this.Star1 = Star1;
        this.Star2 = Star2;
        this.Star3 = Star3;
        this.Star4 = Star4;
        this.Star5 = Star5;
        this.No_of_Votes = No_of_Votes;
        this.Gross = Gross;
        this.Rate = Rate;
        this.id = id;
        this.TMDBId = TMDBId;
        this.Elenco = Elenco;
    }

    public String getPoster_Link() {
        return Poster_Link;
    }

    public void setPoster_Link(String Poster_Link) {
        this.Poster_Link = Poster_Link;
    }

    public String getSeries_Title() {
        return Series_Title;
    }

    public void setSeries_Title(String Series_Title) {
        this.Series_Title = Series_Title;
    }

    public String getReleased_Year() {
        return Released_Year;
    }

    public void setReleased_Year(String Released_Year) {
        this.Released_Year = Released_Year;
    }

    public String getCertificate() {
        return Certificate;
    }

    public void setCertificate(String Certificate) {
        this.Certificate = Certificate;
    }

    public String getRumtime() {
        return Rumtime;
    }

    public void setRumtime(String Rumtime) {
        this.Rumtime = Rumtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getIMDB_Rating() {
        return IMDB_Rating;
    }

    public void setIMDB_Rating(String IMDB_Rating) {
        this.IMDB_Rating = IMDB_Rating;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String Overview) {
        this.Overview = Overview;
    }

    public String getMeta_score() {
        return Meta_score;
    }

    public void setMeta_score(String Meta_score) {
        this.Meta_score = Meta_score;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public String getStar1() {
        return Star1;
    }

    public void setStar1(String Star1) {
        this.Star1 = Star1;
    }

    public String getStar2() {
        return Star2;
    }

    public void setStar2(String Star2) {
        this.Star2 = Star2;
    }

    public String getStar3() {
        return Star3;
    }

    public void setStar3(String Star3) {
        this.Star3 = Star3;
    }

    public String getStar4() {
        return Star4;
    }

    public void setStar4(String Star4) {
        this.Star4 = Star4;
    }

    public String getStar5() {
        return Star5;
    }

    public void setStar5(String Star5) {
        this.Star5 = Star5;
    }

    public String getNo_of_Votes() {
        return No_of_Votes;
    }

    public void setNo_of_Votes(String No_of_Votes) {
        this.No_of_Votes = No_of_Votes;
    }

    public String getGross() {
        return Gross;
    }

    public void setGross(String Gross) {
        this.Gross = Gross;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String Rate) {
        this.Rate = Rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getTMDBId() {
        return TMDBId;
    }

    public void setTMDBId(String TMDBId) {
        this.TMDBId = TMDBId;
    }

    public String getElenco() {
        return Elenco;
    }

    public void setElenco(String Elenco) {
        this.Elenco = Elenco;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    


}
