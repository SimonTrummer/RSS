package at.kaindorf.exa_205rss.database;

import at.kaindorf.exa_205rss.pojos.RSS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RSSRepository extends JpaRepository<RSS,Long> {
}
