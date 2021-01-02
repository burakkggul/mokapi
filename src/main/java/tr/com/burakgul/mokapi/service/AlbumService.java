package tr.com.burakgul.mokapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tr.com.burakgul.mokapi.repository.AlbumRepository;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    Logger logger = LoggerFactory.getLogger(AlbumService.class);

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }
}
