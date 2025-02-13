package pairing.member.dto.requset;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import pairing.member.common.Drinking;
import pairing.member.common.Gender;
import pairing.member.common.Smoking;
import pairing.member.entity.Hobby;
import pairing.member.entity.Member;
import pairing.member.staticData.entity.HobbyList;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ProfileCreate {
    private String name;

    private int age;

    private Gender gender;

    private LocalDate birth;

    private String mbti;

    private Drinking drink;

    private Smoking smoking;

    private String city;

    private String district;

    private List<HobbyList> hobbyList = new ArrayList<>();

    private List<URL> images;

//    private List<URL> photo = new ArrayList<>();
}
