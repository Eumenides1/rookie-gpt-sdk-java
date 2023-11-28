package com.rookie.stack.ai;

import com.rookie.stack.ai.domain.chat.ChatCompletionRequest;
import com.rookie.stack.ai.domain.chat.ChatCompletionResponse;
import com.rookie.stack.ai.domain.edits.EditRequest;
import com.rookie.stack.ai.domain.edits.EditResponse;
import com.rookie.stack.ai.domain.images.ImageRequest;
import com.rookie.stack.ai.domain.images.ImageResponse;
import com.rookie.stack.ai.domain.qa.QACompletionRequest;
import com.rookie.stack.ai.domain.qa.QACompletionResponse;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.*;

import java.util.Map;

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

    /**
     * 文本修复
     *
     * @param editRequest 请求信息；编辑文本的参数
     * @return 应答结果
     */
    @POST("v1/edits")
    Single<EditResponse> edits(@Body EditRequest editRequest);


    /**
     * 生成图片
     * curl https://api.openai.com/v1/images/generations \
     * -H "Content-Type: application/json" \
     * -H "Authorization: Bearer $OPENAI_API_KEY" \
     * -d '{
     * "prompt": "A cute baby sea otter",
     * "n": 2,
     * "size": "1024x1024"
     * }'
     * <p>
     * {
     * "created": 1589478378,
     * "data": [
     * {
     * "url": "https://..."
     * },
     * {
     * "url": "https://..."
     * }
     * ]
     * }
     *
     * @param imageRequest 图片对象
     * @return 应答结果
     */
    @POST("v1/images/generations")
    Single<ImageResponse> genImages(@Body ImageRequest imageRequest);


    /**
     * 修改图片
     * <p>
     * curl https://api.openai.com/v1/images/edits \
     * -H "Authorization: Bearer $OPENAI_API_KEY" \
     * -F image="@otter.png" \
     * -F mask="@mask.png" \
     * -F prompt="A cute baby sea otter wearing a beret" \
     * -F n=2 \
     * -F size="1024x1024"
     * <p>
     * {
     * "created": 1589478378,
     * "data": [
     * {
     * "url": "https://..."
     * },
     * {
     * "url": "https://..."
     * }
     * ]
     * }
     *
     * @param image          图片对象
     * @param mask           图片对象
     * @param requestBodyMap 请求参数
     * @return 应答结果
     */
    @Multipart
    @POST("v1/images/edits")
    Single<ImageResponse> editImages(@Part MultipartBody.Part image, @Part MultipartBody.Part mask, @PartMap Map<String, RequestBody> requestBodyMap);


}
