package dev.elsboo.elasticsearchcore.index;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Analysis {
    private Map<String, Map<String, Object>> tokenizer;
    private Map<String, Map<String, Object>> filter;
    private Map<String, Map<String, Object>> analyzer;
}
