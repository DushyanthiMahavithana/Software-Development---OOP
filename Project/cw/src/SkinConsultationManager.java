import java.io.FileNotFoundException;
import java.io.IOException;

public interface SkinConsultationManager {
    void AddNewDoctor();
    void DeleteDoctor();
    void PrintDoctor();
    void fileSave(String fileName) throws IOException;

}
