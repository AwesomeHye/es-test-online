package dev.elsboo.escore.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NoriAnalyzerRequestVo extends AnalyzerRequestVo {
    private String decompoundMode = "";
    private List<String> userDictionaryRules = new ArrayList<>();
    private boolean discardPunctuation = true;
    private List<String> noriPartOfSpeech = new ArrayList<>();
}
