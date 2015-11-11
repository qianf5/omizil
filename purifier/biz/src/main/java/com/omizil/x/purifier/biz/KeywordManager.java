package com.omizil.x.purifier.biz;

import com.omizil.x.purifier.biz.vo.Keyword;

import java.util.Collection;

/**
 * @author omizil on 15/10/30.
 */
public interface KeywordManager {

    Collection<Keyword> queryWords();

    Keyword getWord(String word);
}
