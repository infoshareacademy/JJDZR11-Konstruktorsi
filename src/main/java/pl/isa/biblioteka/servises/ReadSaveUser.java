package pl.isa.biblioteka.servises;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.isa.biblioteka.model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Service
public class ReadSaveUser {

    public static List<User> readUsers() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get("users.json"));
            ObjectMapper folderUser = new ObjectMapper();
            return Arrays.asList(folderUser.readValue(jsonData, User[].class));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

//    public static void saveUsers() {
//        ObjectMapper mapper = new ObjectMapper();
////        List<User> personList = Users.users;
//        try {
//            mapper.writeValue(new File("users.json"), personList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}

