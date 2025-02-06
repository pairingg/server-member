package pairing.member.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String name;

    private int age;

    @Builder.Default()
    private long heart = 0;

    private boolean gender;

    @Column(unique = true, nullable = false)
    private String email;

    private LocalDate birth;

    private String mbti;

    private boolean drink;

    private boolean smoking;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime joinAt;

    private String residence;

    private String region;

    private int claimCount;

    @OneToMany(mappedBy = "member")
    private List<Hobby> hobbies = new ArrayList<>();;

    @OneToOne
    private Photo photo;
}
