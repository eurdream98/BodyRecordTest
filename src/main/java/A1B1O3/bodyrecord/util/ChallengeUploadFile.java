package A1B1O3.bodyrecord.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class ChallengeUploadFile {

    @Value("${upload.path}")
    private String uploadPath;

    public String saveChallengeImage(MultipartFile challengeImageFile) throws IOException {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + challengeImageFile.getOriginalFilename();
        File saveFile = new File(uploadPath, fileName);
        challengeImageFile.transferTo(saveFile);
        return uploadPath + "/" + fileName;
    }
}
