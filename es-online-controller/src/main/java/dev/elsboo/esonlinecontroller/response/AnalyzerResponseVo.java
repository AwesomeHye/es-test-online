package dev.elsboo.esonlinecontroller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class AnalyzerResponseVo {
    private List<AnalyzedTextInfo> analyzedTextInfoList;
}