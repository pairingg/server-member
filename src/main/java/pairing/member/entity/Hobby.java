package pairing.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pairing.member.converter.StringListConverter;
import pairing.member.staticData.entity.HobbyList;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hobbyId;

    @Convert(converter = StringListConverter.class)
    @Builder.Default
    private List<HobbyList> hobby = new ArrayList<>();

    @ManyToOne
    private Member member;

    public Hobby(Member member, List<HobbyList> hobbyLists) {
        this.member = member;
        this.hobby = hobbyLists;
    }
}
