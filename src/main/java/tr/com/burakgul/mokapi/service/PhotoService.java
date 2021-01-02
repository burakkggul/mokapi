package tr.com.burakgul.mokapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.mokapi.dto.response.InfoResponse;
import tr.com.burakgul.mokapi.dto.request.PhotoRequest;
import tr.com.burakgul.mokapi.dto.response.PhotoResponse;
import tr.com.burakgul.mokapi.model.Photo;
import tr.com.burakgul.mokapi.repository.FileSystemRepository;
import tr.com.burakgul.mokapi.repository.PhotoRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    private final FileSystemRepository fileSystemRepository;

    Logger logger = LoggerFactory.getLogger(PhotoService.class);

    public PhotoService(PhotoRepository photoRepository, FileSystemRepository fileSystemRepository) {
        this.photoRepository = photoRepository;
        this.fileSystemRepository = fileSystemRepository;
    }

    /**
     * TODO unit test will be written
     *
     * @param photoRequests
     * @return
     */
    public ResponseEntity savePhotos(List<PhotoRequest> photoRequests) {
        try {
            for (PhotoRequest photoRequest : photoRequests) {
                String imagePath = fileSystemRepository.save(photoRequest.getImage().getBytes(), photoRequest.getImage().getOriginalFilename());
                String thumbnailPath = fileSystemRepository.save(photoRequest.getThumbnail().getBytes(), photoRequest.getThumbnail().getOriginalFilename());
                photoRepository.save(new Photo(photoRequest, imagePath, thumbnailPath));
            }
            return ResponseEntity
                    .created(URI.create("/photos"))
                    .body(new InfoResponse(HttpStatus.CREATED, "Save successfully completed."));
        } catch (Exception e) {
            logger.error(e.getMessage() + ": " + e.getClass().getCanonicalName() + ": Photo Service savePhotos() error.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Sorry, an error occurred. We will replace this message with a more descriptive one as soon as possible.");

        }
    }

    /**
     * TODO unit test will be written
     * @return
     */
    public ResponseEntity getPhotos() {
        try {
            List<Photo> photos = photoRepository.findAll();
            List<PhotoResponse> photoResponses = new ArrayList<>();
            photos.stream().forEach(p -> {
                photoResponses.add(new PhotoResponse(p));
            });
            return ResponseEntity.ok(photoResponses);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage() + ": " + e.getClass().getCanonicalName() + ": Photo Service getPhotos() error.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Sorry, an error occurred. We will replace this message with a more descriptive one as soon as possible.");
        }
    }
}
