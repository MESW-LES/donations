package les.donations.backendspring.file;

import les.donations.backendspring.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileManagement {

    /**
     * Method that save the files locally in the server file system
     * @param multipartFiles the files received in the HTTP request
     * @param saveToDirectory the directory where the files need to be saved
     * @return a DTO list containing different information about the saved files
     */
    List<FileDTO> saveFiles(MultipartFile [] multipartFiles, String saveToDirectory) throws IOException;
}
