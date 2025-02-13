package pairing.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pairing.member.dto.requset.ProfileCreate;
import pairing.member.entity.Hobby;
import pairing.member.entity.Member;
import pairing.member.entity.Photo;
import pairing.member.repository.HobbyRepository;
import pairing.member.repository.MemberRepository;
import pairing.member.repository.PhotoRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final HobbyRepository hobbyRepository;
    private final PhotoRepository photoRepository;

    @Transactional
    public String postProfile(ProfileCreate profileCreate, String email){
//        Member member = new Member(profileCreate);
        Member byEmail = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));
        Hobby hobby = new Hobby(byEmail, profileCreate.getHobbyList());
        hobbyRepository.save(hobby);

        Photo photo = new Photo(byEmail, profileCreate.getImages());
        photoRepository.save(photo);

        Member detail = byEmail.createDetail(hobby, photo);
        Member save = memberRepository.save(detail.addInfo(profileCreate));

        System.out.println(save.toString());
        return "success";
    }
}
