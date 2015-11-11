package com.omizil.x.purifier.biz.vo;

import java.io.Serializable;

/**
 * @author omizil on 15/10/19.
 */
public class TagPair implements Serializable, Comparable<TagPair> {

    private static final long serialVersionUID = -1274350900670158729L;

    private Keyword keyword;
    private int start;
    private int end;

    public TagPair(String group, String keyword, int start, int end) {
        this(new Keyword(group, keyword), start, end);
    }

    public TagPair(Keyword keyword, int start, int end) {
        this.keyword = keyword;
        this.start = start;
        this.end = end;
    }

    @Override
    public int hashCode() {
        return keyword.getGroup().code().concat("^")
                .concat(keyword.getWord()).concat("^")
                .concat(String.valueOf(start)).concat("^")
                .concat(String.valueOf(end))
                .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
           return false;
        }
        if (!(obj instanceof TagPair)) {
            return false;
        }
        TagPair other = (TagPair) obj;
        return this.keyword.equals(other.keyword)
                && this.start == other.start
                && this.end == other.end;
    }

    @Override
    public int compareTo(TagPair o) {
        if (o == null) {
            return 1;
        }
        if (this.start > o.start) {
            return 1;
        }
        else if (this.start < o.start) {
            return -1;
        }
        if (this.end > o.end) {
            return 1;
        }
        else if (this.end < o.end) {
            return -1;
        }
        return 0;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return this.keyword.getGroup() + "-" + this.keyword.getWord() + "{" + start + "," + end + "}";
    }
}
