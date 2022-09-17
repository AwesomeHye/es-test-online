package dev.hyein.norionline.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AnalyzedTextInfo {
    private String analyzedText;
    private int startOffset;
    private int endOffset;
}
