package com.rookie.stack.ai.domain.other;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author eumenides
 * @description
 * @date 2023/11/27
 */
@Data
public class OpenAiResponse<T> implements Serializable {

    private String object;
    private List<T> data;
    private Error error;


    @Data
    public class Error {
        private String message;
        private String type;
        private String param;
        private String code;
    }

}
