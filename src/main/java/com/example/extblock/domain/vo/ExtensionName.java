package com.example.extblock.domain.vo;

import com.example.extblock.exception.InvalidExtensionException;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@ToString
@Getter
@EqualsAndHashCode(of = "extensionName")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ExtensionName {

    @Column(name = "extension_name",
            unique = true, nullable = false, length = 20)
    private String extensionName;

    public ExtensionName(String extensionName) {
        validateBlankName(extensionName);
        validateEnglishName(extensionName);
        validateMaximumLength(extensionName);
        this.extensionName = extensionName;
    }

    // order 0
    private void validateBlankName(String extensionName) {
        // 띄어쓰기 제거
//        this.extensionName = extensionName.replace(" ", "");
        if (extensionName.isBlank())
            // blank 예외
            throw new InvalidExtensionException("유효하지 않은 확장자명입니다.");
    }

    // order 1
    private void validateEnglishName(String extensionName) {
        // 영어인지, 소문자로 되어있는지. 대문자라면 모두 소문자로 바꾸기
        // 영어인지
        if(!isEnglish(extensionName))
            throw new InvalidExtensionException("확장자명은 영어만 가능합니다.");
        // 소문자로 모두 바꾸기
//        this.extensionName = extensionName.toLowerCase();
    }

    private boolean isEnglish(String extensionName) {
        return Pattern.matches("^[a-zA-Z]*$", extensionName);
    }

    // order 2
    private void validateMaximumLength(String extensionName) {
        if (extensionName.length() > 20) {
            throw new InvalidExtensionException("확장자명의 길이는 20자 이하입니다.");
        }
    }

}
