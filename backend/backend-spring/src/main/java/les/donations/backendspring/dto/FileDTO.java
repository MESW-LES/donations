package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDTO implements ModelDTO {

    @JsonProperty
    public String originalName;
    @JsonProperty
    public String fileName;
    @JsonProperty
    public String directoryPath;
    @JsonProperty
    public String fileExtension;
    @JsonProperty
    public Long fileSize;

    public FileDTO originalName(String originalName) {
        this.originalName = originalName;
        return this;
    }

    public FileDTO fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public FileDTO directoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
        return this;
    }

    public FileDTO fileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
        return this;
    }

    public FileDTO fileSize(Long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "originalName='" + originalName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", directoryPath='" + directoryPath + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
