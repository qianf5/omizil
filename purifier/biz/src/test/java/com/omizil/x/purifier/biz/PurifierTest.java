package com.omizil.x.purifier.biz;

import com.omizil.x.purifier.biz.vo.TagResult;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

/**
 * @author bodu on 15/10/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:META-INF/spring/purifier-biz.xml"
})
public class PurifierTest {

    @Resource
    private Purifier purifier;

    @Test
    public void testPurify() throws Exception {
        String input = "东半球最好用的智能手机";
        List<TagResult> list = purifier.purify(input);
        Assert.assertFalse(list.isEmpty());
    }
}