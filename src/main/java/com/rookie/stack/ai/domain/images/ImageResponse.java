package com.rookie.stack.ai.domain.images;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author eumenides
 * @description
 * @date 2023/11/28
 */
@Data
public class ImageResponse implements Serializable {
    /** 条目数据 */
    private List<Item> data;
    /** 创建时间 */
    private long created;
}
