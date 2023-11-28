package com.rookie.stack.ai.common;

/**
 * @author eumenides
 * @description
 * @date 2023/11/28
 */
public class Constants {
    public final static String NULL = "NULL";

    /**
     * 官网支持的请求角色类型；system、user、assistant
     * https://platform.openai.com/docs/guides/chat/introduction
     */
    public enum Role {

        SYSTEM("system"),
        USER("user"),
        ASSISTANT("assistant"),
        ;

        private String code;

        Role(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

    }
}
