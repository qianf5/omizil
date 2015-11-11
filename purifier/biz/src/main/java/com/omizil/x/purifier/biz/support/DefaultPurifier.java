package com.omizil.x.purifier.biz.support;

import com.omizil.x.purifier.biz.KeywordManager;
import com.omizil.x.purifier.biz.Purifier;
import com.omizil.x.purifier.biz.vo.Keyword;
import com.omizil.x.purifier.biz.vo.TagResult;
import com.omizil.x.purifier.biz.vo.enums.Group;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 默认分析器
 * @author omizil on 15/10/17.
 * @since 1.0.0
 */
public class DefaultPurifier implements Purifier {
    private static final Logger logger = LoggerFactory.getLogger(DefaultPurifier.class);

    @Resource
    private KeywordManager keywordManager;

    @Override
    public List<TagResult> purify(String input) {
        Collection<Keyword> keywords = keywordManager.queryWords();
        List<TagResult> tagResults = new ArrayList<>();
        if (StringUtils.isEmpty(input)) {
            return tagResults;
        }
        if (keywords == null || keywords.isEmpty()) {
            return tagResults;
        }
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 10, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
        List<Future<TagResult>> purifiedResult = new ArrayList<>();
        for (Keyword keyword : keywords) {
            Group group = keyword.getGroup();
            String word = keyword.getWord();
            if (group == null || StringUtils.isEmpty(word)) {
                continue;
            }
            PurifierCallable purifyTask = new PurifierCallable(keyword, input);
            purifiedResult.add(executor.submit(purifyTask));
        }

        for (Future<TagResult> future : purifiedResult) {
            try {
                TagResult result = future.get(1, TimeUnit.SECONDS);
                if (result == null || result.isNotMatch()) {
                    continue;
                }
                tagResults.add(result);
            }
            catch (Exception e) {
                logger.error("Get result failure.", e);
            }
        }

        executor.shutdown();

        return tagResults;
    }
}
