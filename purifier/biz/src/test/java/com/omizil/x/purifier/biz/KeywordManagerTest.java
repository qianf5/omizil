package com.omizil.x.purifier.biz;

import com.omizil.x.purifier.biz.vo.Keyword;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author bodu on 15/10/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:META-INF/spring/purifier-biz.xml"
})
public class KeywordManagerTest {

    @Resource
    private KeywordManager keywordManager;

    @Test
    public void testQueryWords() throws Exception {
        Collection<Keyword> collection = keywordManager.queryWords();
        Assert.assertTrue(!collection.isEmpty());
    }

    @Test
    public void testGetWord() throws Exception {
        String key = "国家级";
        Keyword keyword = keywordManager.getWord(key);
        Assert.assertNotNull(keyword);
    }
}