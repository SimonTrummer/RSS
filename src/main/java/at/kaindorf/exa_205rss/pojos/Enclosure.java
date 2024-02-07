package at.kaindorf.exa_205rss.pojos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Enclosure {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long enclosureId;

    @XmlAttribute
    private String url;

    @XmlAttribute
    private Long length;

    @XmlAttribute
    private String type;
}
