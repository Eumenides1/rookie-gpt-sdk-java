package com.rookie.stack.ai.session;

import com.rookie.stack.ai.domain.chat.ChatCompletionRequest;
import com.rookie.stack.ai.domain.chat.ChatCompletionResponse;
import com.rookie.stack.ai.domain.qa.QACompletionRequest;
import com.rookie.stack.ai.domain.qa.QACompletionResponse;

/**
 * @author eumenides
 * @description OpenAi 会话接口
 * @date 2023/11/27
 */
public interface OpenAiSession {

    /**
     * 文本问答；简单请求
     *
     * @param question 请求信息
     * @return 应答结果
     */
    QACompletionResponse completions(String question);

    /**
     * 文本问答
     *
     * @param qaCompletionRequest 请求信息
     * @return 应答结果
     */
    QACompletionResponse completions(QACompletionRequest qaCompletionRequest);


    /**
     * 问答模型 GPT-3.5/4.0
     *
     * @param chatCompletionRequest 请求信息
     * @return 应答结果
     */
    ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest);




}
