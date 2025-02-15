package pairing.member.dto;

import lombok.Getter;
import org.springframework.context.annotation.Profile;
import pairing.member.common.Drinking;
import pairing.member.common.Gender;
import pairing.member.common.Smoking;
import pairing.member.entity.Hobby;
import pairing.member.entity.Member;
import pairing.member.entity.Photo;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ProfileDTO {
    private String name;

    private int age;

    private Gender gender;

    private LocalDate birth;

    private String mbti;

    private Drinking drink;

    private Smoking smoking;

    private String city;

    private String district;

    private List<String> hobby = new ArrayList<>();

    private List<URL> images = new ArrayList<>();

    public ProfileDTO from(Member member, Hobby hobby, Photo photo) {
        this.name = member.getName();
        this.age = member.getAge();
        this.gender = member.getGender();
        this.birth = member.getBirth();
        this.mbti = member.getMbti();
        this.drink = member.getDrink();
        this.smoking = member.getSmoking();
        this.city = member.getCity();
        this.district = member.getDistrict();
        this.hobby = hobby.getHobby();
        this.images = photo.getPhoto();
        return this;
    }
}
