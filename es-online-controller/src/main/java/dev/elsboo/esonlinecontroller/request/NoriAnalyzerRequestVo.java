package dev.elsboo.esonlinecontroller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NoriAnalyzerRequestVo extends AnalyzerRequestVo {
    private String decompoundMode;
    private List<String> userDictionaryRules;
    private Boolean discardPunctuation;
    private List<String> noriPartOfSpeech;
}
