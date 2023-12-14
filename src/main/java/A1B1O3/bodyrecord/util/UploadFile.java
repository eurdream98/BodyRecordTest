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
        Calendar cal = Calendar.getInstance();
        String yearPath = File.separator +cal.get(Calendar.YEAR) + "";
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

        if(!new File(uploadPath+datePath, datePath).exists()) {
            new File(uploadPath, yearPath).mkdir();
            new File(uploadPath, monthPath).mkdir();
            new File(uploadPath, datePath).mkdir();
        }
        return datePath;

    }

    public String fileUpload(MultipartFile file) throws IOException {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        String dir = makeDir();
        File saveFile = new File(uploadPath + dir, fileName);
        saveFile.createNewFile();
        file.transferTo(saveFile);
        return ("/exerciseimages/" + dir + "/" + fileName);
    }
    public String profileUpload(MultipartFile file) throws IOException {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        String dir = makeDir();
        File saveFile = new File(uploadPath + dir, fileName);
        saveFile.createNewFile();
        file.transferTo(saveFile);
        return ("/profileimages/" + dir + "/" + fileName);
    }
}
