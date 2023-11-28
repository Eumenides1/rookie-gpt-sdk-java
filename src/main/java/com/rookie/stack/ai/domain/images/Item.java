package com.rookie.stack.ai.domain.images;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author eumenides
 * @description 条目
 * @date 2023/11/28
 */
public class Item implements Serializable {
    @JsonProperty("url")
    private String url;
    @JsonProperty("revised_prompt")
    private String revisedPrompt;
}
