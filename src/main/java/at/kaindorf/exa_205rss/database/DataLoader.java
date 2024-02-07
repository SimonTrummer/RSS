package at.kaindorf.exa_205rss.database;

import at.kaindorf.exa_205rss.pojos.RSS;
import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXB;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import at.kaindorf.exa_205rss.pojos.Channel;

import java.net.MalformedURLException;
import java.net.URL;


@RequiredArgsConstructor
@Component
@Slf4j

public class DataLoader {
    private final RSSRepository rssRepository;

    @PostConstruct
    public void initData() {
        System.out.println("Hallo");
        try {

            URL url = new URL("https://www.diepresse.com/rss/Politik");
            RSS rss  = JAXB.unmarshal(url, RSS.class);


            rssRepository.save(rss);
            log.warn("Test");
            log.info(rss.toString());
        } catch (MalformedURLException e) {

            throw new RuntimeException(e);
        }

    }
}
