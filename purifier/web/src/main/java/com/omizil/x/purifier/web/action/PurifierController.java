package com.omizil.x.purifier.web.action;

import com.omizil.x.purifier.biz.Purifier;
import com.omizil.x.purifier.biz.vo.Keyword;
import com.omizil.x.purifier.biz.vo.TagPair;
import com.omizil.x.purifier.biz.vo.TagResult;
import com.omizil.x.purifier.biz.vo.enums.Group;
import com.omizil.x.purifier.web.command.PurifiedInput;
import com.omizil.x.purifier.web.utils.PurifierHelper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author omizil on 15/10/30.
 */
public class PurifierController extends SimpleFormController {

    private Purifier purifier;

    public PurifierController() {
        setCommandClass(PurifiedInput.class);
        setCommandName("purifiedInput");
    }

    @Override
    protected ModelAndView onSubmit(Object command) throws Exception {
        PurifiedInput input = (PurifiedInput) command;

        long start = System.currentTimeMillis();
        List<TagResult> tagResults = purifier.purify(input.getWords());

        Map<Group, Set<Keyword>> keywords = PurifierHelper.extractKeywordsFromResult(tagResults);
        input.setGenericWords(keywords.get(Group.Generic));
        input.setSeriousWords(keywords.get(Group.Serious));

        List<TagPair> tagPairs = PurifierHelper.mergeTagResults(tagResults);
        input.setPurifiedWords(PurifierHelper.fixTag(tagPairs, input.getWords()));

        long end = System.currentTimeMillis();
        input.setCostMillis(end - start);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("purifier");
        mv.addObject("purifiedInput", input);
        mv.addObject("purifiedCount", PurifierHelper.statKeywordsPerfanceCount(tagResults));

        return mv;
    }

    public void setPurifier(Purifier purifier) {
        this.purifier = purifier;
    }
}
