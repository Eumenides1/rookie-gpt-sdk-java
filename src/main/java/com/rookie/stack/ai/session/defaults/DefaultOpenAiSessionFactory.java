package com.rookie.stack.ai.session.defaults;

import com.rookie.stack.ai.session.Configuration;
import com.rookie.stack.ai.session.OpenAiSession;
import com.rookie.stack.ai.session.OpenAiSessionFactory;

/**
 * @author eumenides
 * @description OpenAi API Factory 会话工厂
 * @date 2023/11/27
 */
public class DefaultOpenAiSessionFactory implements OpenAiSessionFactory {
    private final Configuration configuration;

    public DefaultOpenAiSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }
    @Override
    public OpenAiSession openSession() {
        return null;
    }
}
