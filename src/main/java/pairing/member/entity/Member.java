package pairing.member.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import pairing.member.common.Drinking;
import pairing.member.common.Gender;
import pairing.member.common.Smoking;
import pairing.member.dto.requset.ProfileCreate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
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

    private Gender gender;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private String mbti;

//    @Enumerated(EnumType.STRING)
    private Drinking drink;

//    @Enumerated(EnumType.STRING)
    private Smoking smoking;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime joinAt;

    private String city;

    private String district;

    @Builder.Default()
    private int claimCount = 0;

    @Builder.Default()
    private boolean enrolled = false;

    @OneToOne
    private Hobby hobby;;

    @OneToOne
    private Photo photo;

    public Member addInfo(ProfileCreate profileCreate) {
        this.name = profileCreate.getName();
        this.age = profileCreate.getAge();
        this.gender = profileCreate.getGender();
        this.birth = profileCreate.getBirth();
        this.mbti = profileCreate.getMbti();
        this.drink = profileCreate.getDrink();
        this.smoking = profileCreate.getSmoking();
        this.city = profileCreate.getCity();
        this.district = profileCreate.getDistrict();
        enroll();
        return this;
    }

    public Member createDetail(Hobby hobby, Photo photo) {
        this.hobby = hobby;
        this.photo = photo;
        return this;
    }

    private void enroll() {
        this.enrolled = true;
    }

    @Override
    public String toString() {
        return "Member{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", heart=" + heart +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", mbti='" + mbti + '\'' +
                ", drink=" + drink +
                ", smoking=" + smoking +
                ", joinAt=" + joinAt +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", claimCount=" + claimCount +
                ", enrolled=" + enrolled +
                ", hobby=" + hobby +
                ", photo=" + photo +
                '}';
    }
}
