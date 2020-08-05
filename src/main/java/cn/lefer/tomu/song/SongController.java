package cn.lefer.tomu.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/v1/song")
public class SongController {
    SongRepresentationService representationService;

    @Autowired
    public SongController(SongRepresentationService representationService) {
        this.representationService = representationService;
    }

    /**
     * try 20 new songs
     *
     * @return SongRepresentation
     */
    @GetMapping(value = "/explore")
    public Flux<SongRepresentation> getRandomSongs() {
        return Flux.fromStream(representationService.getRandomSongs().stream());
    }
}
