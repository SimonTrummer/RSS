package at.kaindorf.exa_205rss.web;

import at.kaindorf.exa_205rss.database.ChannelRepository;
import at.kaindorf.exa_205rss.database.DataLoader;
import at.kaindorf.exa_205rss.database.RSSRepository;
import at.kaindorf.exa_205rss.pojos.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/channel")
@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelRepository channelRepository;
    private final RSSRepository rssRepository;


    @GetMapping("/all")
    public ResponseEntity<Iterable<Channel>> getAllChannels() {
        return ResponseEntity.ok(channelRepository.findAll());
    }

    @PostMapping("/addChannel")

    public ResponseEntity<Channel> addChannelFromWeb(@RequestBody String channelName) {
        DataLoader dataLoader = new DataLoader(rssRepository);
        Optional<Channel> channelOptional = dataLoader.loadDataByChannel(channelName);
        return channelOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).build());
    }
}
