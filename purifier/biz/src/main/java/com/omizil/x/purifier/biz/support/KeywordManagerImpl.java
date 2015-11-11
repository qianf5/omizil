package com.omizil.x.purifier.biz.support;

import com.omizil.x.purifier.biz.KeywordManager;
import com.omizil.x.purifier.biz.vo.Keyword;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关键字管理实现
 * @author omizil on 15/10/17.
 * @since 1.0.0
 */
public class KeywordManagerImpl implements KeywordManager {
    private static final Logger logger = LoggerFactory.getLogger(KeywordManagerImpl.class);

    private Map<String, Keyword> keywordMap = new HashMap<>();

    protected void initStaticKeywords() {
        try {
            Reader r = new InputStreamReader(KeywordManagerImpl.class.getClassLoader().getResourceAsStream("words.csv"));
            List<String> words = IOUtils.readLines(r);
            for (String word : words) {
                String[] wArray = StringUtils.splitByWholeSeparator(word, ",");
                Keyword keyword = new Keyword(wArray[0], wArray[1], wArray[2]);
                keywordMap.put(keyword.getWord(), keyword);
            }
        }
        catch (Exception e) {
            logger.error("init keywords failure.", e);
        }
    }

    @Override
    public Collection<Keyword> queryWords() {
        return keywordMap.values();
    }

    @Override
    public Keyword getWord(String word) {
        return keywordMap.get(word);
    }
}
