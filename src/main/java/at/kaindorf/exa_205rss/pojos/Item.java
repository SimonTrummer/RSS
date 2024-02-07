package at.kaindorf.exa_205rss.pojos;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long itemId;

    @XmlElement
    @Column(length = 900)
    private String title;

    @XmlElement
    @Column(length = 900)

    private String link;

    @XmlElement
    @Column(length = 900)

    private String description;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @XmlElement
    private Enclosure enclosure;

    @XmlElement
    private String pubDate;

    @XmlElement
    private String guid;
}
