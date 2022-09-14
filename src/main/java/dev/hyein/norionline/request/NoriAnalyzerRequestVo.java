package dev.hyein.norionline.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NoriAnalyzerRequestVo {
    String decompoundMode;
    List<String> userDictionaryRules;
    Boolean discardPunctuation;
    List<String> noriPartOfSpeech;
}
