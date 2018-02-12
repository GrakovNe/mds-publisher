package org.grakovne.mds.publisher.pipeline;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private Map<String, Object> dataSet;

    public Context() {
        dataSet = new HashMap<>();
    }

    public void put(String key, Object data) {
        dataSet.put(key, data);
    }

    public void put(Class key, Object data) {
        dataSet.put(key.getCanonicalName(), data);
    }

    public Object get(String key) {
        return dataSet.get(key);
    }

    public Object get(Class key) {
        return dataSet.get(key.getCanonicalName());
    }

    public void clear() {
        dataSet.clear();
    }

    public void remove(String key) {
        dataSet.remove(key);
    }
}
