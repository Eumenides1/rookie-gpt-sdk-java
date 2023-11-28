package com.rookie.stack.ai;

import com.rookie.stack.ai.domain.chat.ChatCompletionRequest;
import com.rookie.stack.ai.domain.chat.ChatCompletionResponse;
import com.rookie.stack.ai.domain.qa.QACompletionRequest;
import com.rookie.stack.ai.domain.qa.QACompletionResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author jaguarliu
 * @description 以 ChatGPT 官网 API 模型，定义接口。官网：https://platform.openai.com/playground
 * @date 2023-11-27
 */
public interface IOpenAiApi {

    String v1_completions = "v1/completions";

    /**
     * 文本问答
     *
     * @param qaCompletionRequest 请求信息
     * @return 应答结果
     */
    @POST(v1_completions)
    Single<QACompletionResponse> completions(@Body QACompletionRequest qaCompletionRequest);


    String v1_chat_completions = "v1/chat/completions";
    /**
     * 问答模型；默认 GPT-3.5
     *
     * @param chatCompletionRequest 请求信息
     * @return 应答结果
     */
    @POST(v1_chat_completions)
    Single<ChatCompletionResponse> completions(@Body ChatCompletionRequest chatCompletionRequest);


}
