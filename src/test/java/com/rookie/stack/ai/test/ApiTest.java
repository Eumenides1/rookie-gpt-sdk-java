package com.rookie.stack.ai.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookie.stack.ai.common.Constants;
import com.rookie.stack.ai.domain.chat.ChatCompletionRequest;
import com.rookie.stack.ai.domain.chat.ChatCompletionResponse;
import com.rookie.stack.ai.domain.chat.Message;
import com.rookie.stack.ai.domain.qa.QACompletionResponse;
import com.rookie.stack.ai.session.Configuration;
import com.rookie.stack.ai.session.OpenAiSession;
import com.rookie.stack.ai.session.OpenAiSessionFactory;
import com.rookie.stack.ai.session.defaults.DefaultOpenAiSessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

/**
 * @author eumenides
 * @description 单元测试
 * @date 2023/11/28
 */
@Slf4j
public class ApiTest {

    private OpenAiSession openAiSession;
    @Before
    public void test_OpenAiSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.setApiHost("https://api.openai.com/");
        configuration.setApiKey("");
        // 会话工厂
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        // 开启会话
        this.openAiSession = factory.openSession();
    }

    /**
     * 简单问答模式
     */
    @Test
    public void test_qa_completions() throws JsonProcessingException {
        QACompletionResponse response01 = openAiSession.completions("写个java冒泡排序");
        log.info("测试结果：{}", new ObjectMapper().writeValueAsString(response01.getChoices()));
    }

    /**
     * 此对话模型 3.5 接近于官网体验
     */
    @Test
    public void test_chat_completions() {
        // 1. 创建参数
        ChatCompletionRequest chatCompletion = ChatCompletionRequest
                .builder()
                .messages(Collections.singletonList(Message.builder().role(Constants.Role.USER).content("写一个java冒泡排序").build()))
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
                .build();
        // 2. 发起请求
        ChatCompletionResponse chatCompletionResponse = openAiSession.completions(chatCompletion);
        // 3. 解析结果
        chatCompletionResponse.getChoices().forEach(e -> {
            log.info("测试结果：{}", e.getMessage());
        });
    }

}
