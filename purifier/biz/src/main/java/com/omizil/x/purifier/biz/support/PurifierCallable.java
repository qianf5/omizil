package com.omizil.x.purifier.biz.support;

import com.omizil.x.purifier.biz.vo.Keyword;
import com.omizil.x.purifier.biz.vo.TagResult;

import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分析执行
 * @author omizil on 15/10/17.
 * @since 1.0.0
 */
public class PurifierCallable implements Callable<TagResult> {

    private Keyword keyword;
    private String input;
    private Pattern keyWorkPattern;

    public PurifierCallable(Keyword keyword, String input) {
        this.keyword = keyword;
        this.input = input;
        keyWorkPattern = Pattern.compile(keyword.getWord());
    }

    @Override
    public TagResult call() throws Exception {
        TagResult result = new TagResult(keyword);

        Matcher keyWordMatcher = keyWorkPattern.matcher(input);

        while (keyWordMatcher.find()) {
            result.addIndex(keyWordMatcher.start(), keyWordMatcher.end()-1);
        }

        return result;
    }
}
