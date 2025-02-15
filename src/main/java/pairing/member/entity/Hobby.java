package pairing.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pairing.member.converter.StringListConverter;
import pairing.member.staticData.entity.HobbyList;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hobbyId;

    @Convert(converter = StringListConverter.class)
    @Builder.Default
    private List<String> hobby = new ArrayList<>();

    @OneToOne
    private Member member;

    public Hobby(Member member, List<String> hobby) {
        this.member = member;
        this.hobby = hobby;
    }
}
