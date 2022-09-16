package dev.hyein.norionline.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hyein.norionline.common.RestDocsConfiguration;
import dev.hyein.norionline.request.NoriAnalyzerRequestVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class) // @Configuration 적용
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
                .andExpect(MockMvcResultMatchers.jsonPath("analyzedText").exists())

                .andDo(document("analyze-nori", // 문서명
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header")
                        ),

                        requestParameters(
                                parameterWithName("decompoundMode").description("분석 모드"),
                                parameterWithName("userDictionaryRules").description("유저 사전"),
                                parameterWithName("discardPunctuation").description("기호 제거 여부"),
                                parameterWithName("noriPartOfSpeech").description("스탑태그"),
                                parameterWithName("inputText").description("입력 텍스트")
                        )
                ))
        ;
    }

    private NoriAnalyzerRequestVo createVo() {
        return new NoriAnalyzerRequestVo("compound", Arrays.asList("가곡역 가곡 역"), true, Arrays.asList("NN"));
    }

}