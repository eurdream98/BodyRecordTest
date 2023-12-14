package A1B1O3.bodyrecord.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

@Component
public class UploadFile {
    @Value("${image.image-dir}")
    private String uploadPath;


    public String makeDir(){

        if(!new File(uploadPath).exists()) {
            new File(uploadPath).mkdir();
        }
        return null;

    }

    public String fileUpload(MultipartFile file) throws IOException {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        String dir = makeDir();
        File saveFile = new File(uploadPath, fileName);
        saveFile.createNewFile();
        file.transferTo(saveFile);
        return fileName;
    }

}
