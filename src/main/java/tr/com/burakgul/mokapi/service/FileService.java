package tr.com.burakgul.mokapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.mokapi.repository.FileSystemRepository;

import java.nio.file.Paths;

@Service
public class FileService {

    private final FileSystemRepository fileSystemRepository;

    Logger logger = LoggerFactory.getLogger(FileService.class);

    public FileService(FileSystemRepository fileSystemRepository){
        this.fileSystemRepository = fileSystemRepository;
    }

    /**
     * TODO unit test will be written
     * It will start reading our file only when we use it. In our case, it'll be when sending it to the client
     * via the RestController. Also, it'll stream the file content from the filesystem to the user, saving us
     * from loading all the bytes into memory.
     * @param path
     * @return
     */
    public FileSystemResource getFileByPath(String path){
        try {
            return new FileSystemResource(Paths.get(path));
        }catch (Exception e){
            logger.error(e.getMessage()+": "+e.getClass().getCanonicalName()+": FileService getFileByPath() error.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Sorry, an error occurred. We will replace this message with a more descriptive one as soon as possible.");
        }
    }

}
