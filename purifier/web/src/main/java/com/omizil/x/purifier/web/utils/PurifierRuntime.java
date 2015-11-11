package com.omizil.x.purifier.web.utils;

import com.omizil.x.purifier.biz.vo.TagPair;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author omizil on 15/10/19.
 */
public class PurifierRuntime {
    private Iterator<TagPair> pairIterator;
    private TagPair nextPair;
    private TagPair currentPair;
    private final Set<TagPair> runtimePairs = new LinkedHashSet<>();
    private Iterator<TagPair> runtimePairIterator;

    private PurifierRuntime() {
    }

    public static PurifierRuntime init(Iterator<TagPair> iterator) {
        PurifierRuntime runtime = new PurifierRuntime();
        runtime.pairIterator = iterator;
        return runtime;
    }

    public boolean isRuntimePairExist() {
        return !runtimePairs.isEmpty();
    }

    public Set<TagPair> nextRangePairs() {
        currentPair = null;
        if (nextPair != null) {
            currentPair = nextPair;
            runtimePairs.add(currentPair);
        }
        while (pairIterator.hasNext()) {
            nextPair = pairIterator.next();
            if (currentPair == null) {
                currentPair = nextPair;
            }
            if (nextPair.getStart() != currentPair.getStart()) {
                break;
            }
            runtimePairs.add(nextPair);
            nextPair = null;
        }
        runtimePairIterator = runtimePairs.iterator();
        return runtimePairs;
    }

    public TagPair nextRuntimePair() {
        currentPair = null;
        if (runtimePairIterator.hasNext()) {
            currentPair = runtimePairIterator.next();
        }
        return currentPair;
    }

    public void removeRuntimePair(TagPair tagPair) {
        runtimePairs.remove(tagPair);
        runtimePairIterator = runtimePairs.iterator();
    }

    public TagPair getCurrentPair() {
        return currentPair;
    }

    public Set<TagPair> getRuntimePairs() {
        return runtimePairs;
    }

}
