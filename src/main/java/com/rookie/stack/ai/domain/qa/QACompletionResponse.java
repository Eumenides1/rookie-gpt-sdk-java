package com.rookie.stack.ai.domain.qa;

import com.rookie.stack.ai.domain.other.Choice;
import com.rookie.stack.ai.domain.other.Usage;
import lombok.Data;


import java.io.Serializable;

/**
 * @author eumenides
 * @description
 * @date 2023/11/27
 */
@Data
public class QACompletionResponse implements Serializable {
    /** ID */
    private String id;
    /** 对象 */
    private String object;
    /** 模型 */
    private String model;
    /** 对话 */
    private Choice[] choices;
    /** 创建 */
    private long created;
    /** 耗材 */
    private Usage usage;
}
