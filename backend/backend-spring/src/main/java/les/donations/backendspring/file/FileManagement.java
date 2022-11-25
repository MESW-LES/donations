package les.donations.backendspring.file;

import les.donations.backendspring.dto.FileDTO;
import les.donations.backendspring.util.Constant;
import les.donations.backendspring.util.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileManagement implements IFileManagement{

    @Override
    public List<FileDTO> saveFiles(MultipartFile[] multipartFiles, String saveToDirectory) throws IOException {
        List<FileDTO> files = new ArrayList<>();
        // for each multipart file received in the request, it gets the file
        for(MultipartFile multipartFile : multipartFiles){
            // generates a random UUID
            String fileName = UUID.randomUUID().toString();
            // creates a new file to save the multipart file in the file system
            File file = new File(saveToDirectory + Constant.SLASH + fileName + Constant.POINT + Constant.PNG);
            // transfers the multipart file to the file in the file system
            multipartFile.transferTo(file);
            // creates a dto with information about the file
            files.add(new FileDTO().fileName(fileName).directoryPath(saveToDirectory).fileExtension(Constant.PNG)
                    .fileSize(multipartFile.getSize()).originalName(FilenameUtils.getFileName(multipartFile.getOriginalFilename())));
        }
        return files;
    }
}
