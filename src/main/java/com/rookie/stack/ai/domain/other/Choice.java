package com.rookie.stack.ai.domain.other;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author eumenides
 * @description 对话信息
 * @date 2023/11/27
 */
@Data
public class Choice implements Serializable {
    private long index;
    private String text;
    private Object logprobs;
    @JsonProperty("finish_reason")
    private String finishReason;
}
