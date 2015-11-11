package com.omizil.x.purifier.biz.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author omizil on 15/10/17.
 */
public class TagResult implements Serializable {
    private static final long serialVersionUID = -4320480142294764358L;

    private Keyword keyword;
    private final List<TagPair> indexPairs = new ArrayList<>();

    public TagResult() {
    }

    public TagResult(Keyword keyword) {
        this.keyword = keyword;
    }

    public TagResult(String group, String keyword) {
        this(new Keyword(group, keyword));
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public Iterator<TagPair> getIndexPairs() {
        return indexPairs.iterator();
    }

    public TagResult addIndex(int start, int end) {
        indexPairs.add(new TagPair(keyword, start, end));
        return this;
    }

    public TagResult addIndex(TagPair indexPair) {
        indexPairs.add(indexPair);
        return this;
    }

    public boolean isNotMatch() {
        return indexPairs.isEmpty();
    }

    public int getMatchCount() {
        return indexPairs.size();
    }

    @Override
    public String toString() {
        return indexPairs.toString();
    }
}
