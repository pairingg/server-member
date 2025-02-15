package pairing.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pairing.member.dto.ProfileDTO;
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
    public String postProfile(ProfileDTO profileDTO, String email){
        Member byEmail = findByEmail(email);
        Hobby hobby = new Hobby(byEmail, profileDTO.getHobby());
        hobbyRepository.save(hobby);

        Photo photo = new Photo(byEmail, profileDTO.getImages());
        photoRepository.save(photo);

        Member detail = byEmail.createDetail(hobby, photo);
        memberRepository.save(detail.addInfo(profileDTO));
        return "success";
    }

    public ProfileDTO getProfile(String email){
        Member byEmail = findByEmail(email);
        Hobby hobby = hobbyRepository.findByMember(byEmail)
                .orElseThrow(()->new IllegalArgumentException("Invalid hobby"));
        Photo photo = photoRepository.findByMember(byEmail)
                .orElseThrow(() -> new IllegalArgumentException("Invalid photo"));
        ProfileDTO profileDTO = new ProfileDTO();
        return profileDTO.from(byEmail, hobby, photo);
    }

    public ProfileDTO getOtherProfile(long userId){
        Member byId = memberRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("Invalid member"));
        calHeart(byId, 1);
        return getProfile(byId.getEmail());
    }

    @Async
    public void calHeart(Member member,long score){
        member.calHeart(score);
        memberRepository.save(member);
    }

    private Member findByEmail(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));
    }
}
