package at.kaindorf.exa_205rss.database;

import org.springframework.data.jpa.repository.JpaRepository;
import at.kaindorf.exa_205rss.pojos.Channel;

public interface ChannelRepository extends JpaRepository<Channel,Long> {
}
