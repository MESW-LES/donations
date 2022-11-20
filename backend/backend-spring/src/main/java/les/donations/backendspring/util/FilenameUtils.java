package les.donations.backendspring.util;

public class FilenameUtils {

    public static String getFileName(String file){
        if(file == null || file.isEmpty()){
            return "";
        }
        // \test\file.png -> \test\file -> file
        String [] dividedFile = file.split("\\" + Constant.POINT)[0].split(Constant.SLASH);
        return dividedFile[dividedFile.length - 1];
    }
}
