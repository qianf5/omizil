package com.omizil.x.purifier.biz;

import com.omizil.x.purifier.biz.vo.TagResult;

import java.util.List;

/**
 * @author omizil on 15/10/30.
 */
public interface Purifier {

    List<TagResult> purify(String input);
}
