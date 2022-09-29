package dev.elsboo.esonline.index;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@JsonRootName(value = "analysis")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Analysis {
    private Map<String, Map<String, Object>> tokenizer = new HashMap<>();   // key: tokenizer 명
    private Map<String, Map<String, Object>> filter = new HashMap<>();      // key: filter 명
    private Map<String, Map<String, Object>> analyzer = new HashMap<>();    // key: analyzer 명
}
