package com.rookie.stack.ai.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookie.stack.ai.common.Constants;
import com.rookie.stack.ai.domain.chat.ChatCompletionRequest;
import com.rookie.stack.ai.domain.chat.ChatCompletionResponse;
import com.rookie.stack.ai.domain.chat.Message;
import com.rookie.stack.ai.domain.edits.EditRequest;
import com.rookie.stack.ai.domain.edits.EditResponse;
import com.rookie.stack.ai.domain.images.ImageResponse;
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
        String apiKey = System.getenv("MY_API_KEY");
        configuration.setApiKey(apiKey);
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

    /**
     * 文本修复
     */
    @Test
    public void test_edit() {
        // 文本请求
        EditRequest textRequest = EditRequest.builder()
                .input("因为哎所以爱")
                .instruction("帮我修改错字")
                .model(EditRequest.Model.TEXT_DAVINCI_EDIT_001.getCode()).build();
        EditResponse textResponse = openAiSession.edit(textRequest);
        log.info("测试结果：{}", textResponse);

        // 代码请求
        EditRequest codeRequest = EditRequest.builder()
                // j <= 10 应该修改为 i <= 10
                .input("for (int i = 1; j <= 10; i++) {\n" +
                        "    System.out.println(i);\n" +
                        "}")
                .instruction("这段代码执行时报错，请帮我修改").model(EditRequest.Model.CODE_DAVINCI_EDIT_001.getCode()).build();
        EditResponse codeResponse = openAiSession.edit(codeRequest);
        log.info("测试结果：{}", codeResponse);
    }

    /**
     * 生成图片
     */
    @Test
    public void test_genImages() {
        // 方式1，简单调用
        ImageResponse imageResponse01 = openAiSession.genImages("画一个996加班的程序员");
        log.info("测试结果：{}", imageResponse01);

//        // 方式2，调参调用
//        ImageResponse imageResponse02 = openAiSession.genImages(ImageRequest.builder()
//                .prompt("画一个996加班的程序员")
//                .size(ImageEnum.Size.size_256.getCode())
//                .responseFormat(ImageEnum.ResponseFormat.B64_JSON.getCode()).build());
//        log.info("测试结果：{}", imageResponse02);
    }



}
