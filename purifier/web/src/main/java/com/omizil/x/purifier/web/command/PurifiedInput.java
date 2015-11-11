package com.omizil.x.purifier.web.command;

import com.omizil.x.purifier.biz.vo.Keyword;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author omizil on 15/10/30.
 */
public class PurifiedInput implements Serializable {

    private static final long serialVersionUID = -7622945546853349190L;

    private String words;
    private String purifiedWords;
    private Set<Keyword> genericWords = new LinkedHashSet<>();
    private Set<Keyword> seriousWords = new LinkedHashSet<>();
    private double costSecond;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getPurifiedWords() {
        return purifiedWords;
    }

    public void setPurifiedWords(String purifiedWords) {
        this.purifiedWords = purifiedWords;
    }

    public Set<Keyword> getGenericWords() {
        return genericWords;
    }

    public void setGenericWords(Set<Keyword> genericWords) {
        if (genericWords == null) {
            this.genericWords = new LinkedHashSet<>();
        }
        else {
            this.genericWords = genericWords;
        }
    }

    public Set<Keyword> getSeriousWords() {
        return seriousWords;
    }

    public void setSeriousWords(Set<Keyword> seriousWords) {
        if (seriousWords == null) {
            this.seriousWords = new LinkedHashSet<>();
        }
        else {
            this.seriousWords = seriousWords;
        }
    }

    public double getCostSecond() {
        return costSecond;
    }

    public void setCostSencond(double costSecond) {
        this.costSecond = costSecond;
    }

    public void setCostMillis(long millis) {
        this.costSecond = millis * 0.001;
    }
}
