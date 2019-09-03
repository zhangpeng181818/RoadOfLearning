package com.nuc.zp.sourcecode.types;

import java.util.List;
import java.util.Map;

public class ResolvableTypeBean {
    private List<String> listString;
    private List<List<String>> listLists;
    private Map<String, Long> maps;
    private Parent<String> parent;

    public Map<String, Long> getMaps() {
        return maps;
    }
}
