package com.rookie.stack.ai.session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rookie.stack.ai.domain.chat.ChatCompletionRequest;
import com.rookie.stack.ai.domain.chat.ChatCompletionResponse;
import com.rookie.stack.ai.domain.edits.EditRequest;
import com.rookie.stack.ai.domain.edits.EditResponse;
import com.rookie.stack.ai.domain.images.ImageRequest;
import com.rookie.stack.ai.domain.images.ImageResponse;
import com.rookie.stack.ai.domain.qa.QACompletionRequest;
import com.rookie.stack.ai.domain.qa.QACompletionResponse;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.util.concurrent.CompletableFuture;

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
     * 文本问答 & 流式反馈
     *
     * @param qaCompletionRequest 请求信息
     * @param eventSourceListener 实现监听；通过监听的 onEvent 方法接收数据
     */
    EventSource completions(QACompletionRequest qaCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;

    /**
     * 问答模型 GPT-3.5/4.0
     *
     * @param chatCompletionRequest 请求信息
     * @return 应答结果
     */
    ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest);

    /**
     * 问答模型 GPT-3.5/4.0 & 流式反馈
     *
     * @param chatCompletionRequest 请求信息
     * @param eventSourceListener   实现监听；通过监听的 onEvent 方法接收数据
     * @return 应答结果
     */
    EventSource chatCompletions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;

    /**
     * 问答模型 GPT-3.5/4.0 & 流式反馈
     *
     * @param apiHostByUser         自定义host
     * @param apiKeyByUser          自定义Key
     * @param chatCompletionRequest 请求信息
     * @param eventSourceListener   实现监听；通过监听的 onEvent 方法接收数据
     * @return 应答结果
     */
    EventSource chatCompletions(String apiHostByUser, String apiKeyByUser, ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;


    /**
     * 问答模型 GPT-3.5/4.0 & 流式反馈 & 一次反馈
     *
     * @param chatCompletionRequest 请求信息
     * @return 应答结果
     */
    CompletableFuture<String> chatCompletions(ChatCompletionRequest chatCompletionRequest) throws InterruptedException, JsonProcessingException;

    /**
     * 文本修复
     *
     * @param editRequest 请求信息
     * @return 应答结果
     */
    EditResponse edit(EditRequest editRequest);

    /**
     * 生成图片
     *
     * @param prompt 图片描述
     * @return 应答结果
     */
    ImageResponse genImages(String prompt);

    /**
     * 生成图片
     *
     * @param imageRequest 图片描述
     * @return 应答结果
     */
    ImageResponse genImages(ImageRequest imageRequest);


}
