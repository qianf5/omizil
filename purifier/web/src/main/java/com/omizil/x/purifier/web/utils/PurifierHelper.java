package com.omizil.x.purifier.web.utils;

import com.omizil.x.purifier.biz.vo.Keyword;
import com.omizil.x.purifier.biz.vo.TagPair;
import com.omizil.x.purifier.biz.vo.TagResult;
import com.omizil.x.purifier.biz.vo.enums.Group;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * @author omizil on 15/10/30.
 */
public abstract class PurifierHelper {

    private static final Map<Group, String> GROUP_CSS_MAP = new HashMap<>();

    static {
        GROUP_CSS_MAP.put(Group.Generic, "y-rule");
        GROUP_CSS_MAP.put(Group.Serious, "g-rule");
    }

    public static List<TagPair> mergeTagResults(List<TagResult> tagResults) {
        if (tagResults == null || tagResults.isEmpty()) {
            return new ArrayList<>();
        }

        List<TagPair> pairs = new ArrayList<>();
        for (TagResult tagResult : tagResults) {
            Iterator<TagPair> pairIterator = tagResult.getIndexPairs();
            while (pairIterator.hasNext()) {
                pairs.add(pairIterator.next());
            }
        }

        TagPair[] pairArray = pairs.toArray(new TagPair[pairs.size()]);

        Arrays.sort(pairArray);

        return Arrays.asList(pairArray);
    }

    public static String fixTag(List<TagPair> pairs, String input) {
        if (pairs == null || pairs.isEmpty()) {
            return input;
        }
        if (StringUtils.isBlank(input)) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        char[] inputChars = input.toCharArray();
        PurifierRuntime purifierRuntime = PurifierRuntime.init(pairs.iterator());
        purifierRuntime.nextRangePairs();

        for (int i = 0; i < inputChars.length; i++) {
            TagPair currentTagPair = purifierRuntime.getCurrentPair();
            if (currentTagPair == null) {
                result.append(inputChars[i]);
                continue;
            }

            if (i == currentTagPair.getStart()) {
                String fix = "";
                for (TagPair pair : purifierRuntime.getRuntimePairs()) {
                    Keyword keyword = pair.getKeyword();
                    fix = "<span class='" + GROUP_CSS_MAP.get(keyword.getGroup())
                            + "' k='" + keyword.getWord() + "'>" + fix;
                }
                result.append(fix);
            }

            result.append(inputChars[i]);

            if (i == currentTagPair.getEnd()) {
                result.append("</span>");
                purifierRuntime.removeRuntimePair(currentTagPair);
                currentTagPair = purifierRuntime.nextRuntimePair();
                if (currentTagPair == null) {
                    purifierRuntime.nextRangePairs();
                }
            }
        }

        return result.toString();
    }

    public static Map<Group, Set<Keyword>> extractKeywordsFromResult(List<TagResult> tagResults) {
        Map<Group, Set<Keyword>> groupMap = new HashMap<>();
        for (TagResult result : tagResults) {
            Keyword keyword = result.getKeyword();
            Group group = keyword.getGroup();
            Set<Keyword> keywords = groupMap.get(group);
            if (keywords == null) {
                keywords = new LinkedHashSet<>();
                groupMap.put(group, keywords);
            }
            keywords.add(keyword);
        }
        return groupMap;
    }

    public static int statKeywordsPerfanceCount(List<TagResult> tagResults) {
        int count = 0;
        for (TagResult result : tagResults) {
            count = count + result.getMatchCount();
        }
        return count;
    }
}
