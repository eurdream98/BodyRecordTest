package A1B1O3.bodyrecord.body.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class BodyRequest {
    @NotNull(message="이미지를 넣어주세요")
    private final String memberImage;
    @NotNull(message="닉네임을 입력해주세요")
    private final String memberNickname;
    @NotNull(message="몸무게를 입력해주세요")
    private final float weight;
    @NotNull(message="골격근량을 입력해주세요")
    private final float muscle;
    @NotNull(message="체지방률 입력해주세요")
    private final float fat;
    private final MultipartFile imgFile;
}
