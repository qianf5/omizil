package com.omizil.x.purifier.biz.vo;

import com.omizil.x.purifier.biz.vo.enums.Group;

import java.io.Serializable;

/**
 * @author omizil on 15/10/17.
 */
public class Keyword implements Serializable {
    private static final long serialVersionUID = -1445888991511047129L;

    private String word;
    private Group group;
    private String law;

    public Keyword() {
    }

    public Keyword(String word, String group, String law) {
        this.word = word;
        this.group = Group.getByCode(group);
        this.law = law;
    }

    public Keyword(String word, String group) {
        this.word = word;
        this.group = Group.getByCode(group);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getLaw() {
        return law;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    @Override
    public int hashCode() {
        return this.group.code().concat("^").concat(this.word).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Keyword)) {
            return false;
        }
        Keyword other = (Keyword) obj;
        return this.group.equals(other.group) && this.word.equals(other.word);
    }
}
