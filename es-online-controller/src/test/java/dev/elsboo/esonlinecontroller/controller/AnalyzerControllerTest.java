package dev.elsboo.esonlinecontroller.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.elsboo.esonlinecontroller.common.RestDocsConfiguration;
import dev.elsboo.esonlinecontroller.request.NoriAnalyzerRequestVo;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class) // @TestConfiguration 적용
class AnalyzerControllerTest {

    @Autowired(required = false)
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void analyzer() throws Exception {
        NoriAnalyzerRequestVo noriAnalyzerRequestVo = createVo();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/analyze/nori")
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                        .param("decompoundMode", "compound")
                        .param("userDictionaryRules", "가곡역 가곡 역")
                        .param("discardPunctuation", "true")
                        .param("noriPartOfSpeech", "NN")
                        .param("inputText", "아버지가 가방에 들어가고 싶다.")
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.analyzedTextInfoList", Matchers.hasSize(Matchers.not(0))))

                .andDo(document("analyze-nori", // 문서명
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header")
                        ),

                        requestParameters(
                                parameterWithName("decompoundMode").description("복합 명사 옵션").attributes(
                                        key("available").value("none,discard,mixed")
                                ),
                                parameterWithName("userDictionaryRules").description("유저 사전").optional(),
                                parameterWithName("discardPunctuation").description("기호 제거 여부"),
                                parameterWithName("noriPartOfSpeech").description("stop tags").attributes(
                                        key("available").value("E,IC,J,MAG,MM,NA,NR,SC,SE,SF,SH,SL,SN,SP,SSC,SSO,SY,UNA,UNKNOWN,VA,VCN,VCP,VSV,VV,VX,XPN,XR,XSA,XSN,XSV")
                                ),
                                parameterWithName("inputText").description("입력 텍스트").optional()
                        ),

                        responseFields(
                                fieldWithPath("analyzedTextInfoList[0].analyzedText").description("분석 완료된 텍스트"),
                                fieldWithPath("analyzedTextInfoList[0].startOffset").description("텀 시작 위치"),
                                fieldWithPath("analyzedTextInfoList[0].endOffset").description("텀 끝 위치")
                        )
                ))
        ;
    }

    private NoriAnalyzerRequestVo createVo() {
        return new NoriAnalyzerRequestVo("compound", Arrays.asList("가곡역 가곡 역"), true, Arrays.asList("NN"));
    }

}