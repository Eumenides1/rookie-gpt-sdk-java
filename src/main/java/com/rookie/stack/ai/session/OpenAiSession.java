package com.rookie.stack.ai.session;

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

}
