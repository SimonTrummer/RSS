package at.kaindorf.exa_205rss.database;

import at.kaindorf.exa_205rss.pojos.Item;
import at.kaindorf.exa_205rss.pojos.RSS;
import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXB;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import at.kaindorf.exa_205rss.pojos.Channel;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Component
@Slf4j
public class DataLoader {
    private final RSSRepository rssRepository;

    public Optional<Channel> loadDataByChannel(String channelName) {

        try {
            Set<RSS> rssInDB = new HashSet<>(rssRepository.findAll());
            Set<Channel> channelSet = new HashSet<>();
            Set<Item> itemSet = new HashSet<>();

            URL url = new URL("https://www.diepresse.com/rss/" + channelName);
            RSS rss = JAXB.unmarshal(url, RSS.class);

            log.info("RSS loading successful");

            channelSet = rssInDB.stream()
                    .map(RSS::getChannel)
                    .collect(Collectors.toSet());


            channelSet.add(rss.getChannel());
            Channel newChannel = channelSet.stream()
                    .filter(channel -> channel.equals(rss.getChannel()))
                    .findFirst()
                    .orElse(null);

            itemSet = channelSet.stream().flatMap(c -> c.getItems().stream()).collect(Collectors.toSet());

            itemSet.addAll(rss.getChannel().getItems());

            assert newChannel != null;
            newChannel.setItems(itemSet.stream().filter(item -> rss.getChannel().getItems().contains(item)).collect(Collectors.toList()));

            rss.setChannel(newChannel);

            rssRepository.save(rss);

            return Optional.of(rss.getChannel());
        } catch (MalformedURLException e) {
            return Optional.empty();
        }

    }
}
