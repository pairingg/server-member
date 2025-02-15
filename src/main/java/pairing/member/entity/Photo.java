package pairing.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pairing.member.converter.UrlListConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long photoId;

    @Convert(converter = UrlListConverter.class)
    @Builder.Default
    private List<URL> photo = new ArrayList<>();

    @OneToOne
    private Member member;

    public Photo(Member save, List<URL> photo) {
        this.member = save;
        this.photo = photo;
    }
}
