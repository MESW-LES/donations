package les.donations.backendspring.mapper.donationImage;

import les.donations.backendspring.dto.FileDTO;
import les.donations.backendspring.model.DonationImage;
import org.springframework.stereotype.Component;

@Component
public class DonationImageMapper implements IDonationImageMapper {

    @Override
    public DonationImage dtoToModel(FileDTO fileDTO) throws IllegalArgumentException{
        return new DonationImage(fileDTO.originalName, fileDTO.fileName, fileDTO.directoryPath, fileDTO.fileExtension,
                fileDTO.fileSize);
    }
}
