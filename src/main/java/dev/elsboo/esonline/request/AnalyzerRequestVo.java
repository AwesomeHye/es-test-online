package dev.elsboo.esonline.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AnalyzerRequestVo {
    private String indexName;
    private String inputText;
}
