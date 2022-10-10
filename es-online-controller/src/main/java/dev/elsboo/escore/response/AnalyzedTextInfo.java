package dev.elsboo.escore.response;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class AnalyzedTextInfo {
    private String token;
    private Long startOffset;
    private Long endOffset;
}
