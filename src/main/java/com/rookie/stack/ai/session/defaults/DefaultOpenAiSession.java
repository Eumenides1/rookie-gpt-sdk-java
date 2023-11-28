package com.rookie.stack.ai.session.defaults;

import com.rookie.stack.ai.IOpenAiApi;
import com.rookie.stack.ai.domain.chat.ChatCompletionRequest;
import com.rookie.stack.ai.domain.chat.ChatCompletionResponse;
import com.rookie.stack.ai.domain.edits.EditRequest;
import com.rookie.stack.ai.domain.edits.EditResponse;
import com.rookie.stack.ai.domain.qa.QACompletionRequest;
import com.rookie.stack.ai.domain.qa.QACompletionResponse;
import com.rookie.stack.ai.session.Configuration;
import com.rookie.stack.ai.session.OpenAiSession;
import io.reactivex.Single;
import okhttp3.sse.EventSource;

/**
 * @author eumenides
 * @description
 * @date 2023/11/28
 */
public class DefaultOpenAiSession implements OpenAiSession {

    /**
     * 配置信息
     */
    private final Configuration configuration;

    /**
     * Open Ai 接口
     */
    private final IOpenAiApi openAiApi;

    /**
     * 工厂事件
     */
    private final EventSource.Factory factory;

    public DefaultOpenAiSession(Configuration configuration) {
        this.configuration = configuration;
        this.openAiApi = configuration.getOpenAiApi();
        this.factory = configuration.createRequestFactory();
    }
    @Override
    public QACompletionResponse completions(String question) {
        QACompletionRequest request = QACompletionRequest
                .builder()
                .prompt(question)
                .build();
        Single<QACompletionResponse> completions = this.openAiApi.completions(request);
        return completions.blockingGet();
    }

    @Override
    public QACompletionResponse completions(QACompletionRequest qaCompletionRequest) {
        return this.openAiApi.completions(qaCompletionRequest).blockingGet();
    }

    @Override
    public ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest) {
        return this.openAiApi.completions(chatCompletionRequest).blockingGet();
    }

    @Override
    public EditResponse edit(EditRequest editRequest) {
        return this.openAiApi.edits(editRequest).blockingGet();
    }
}
