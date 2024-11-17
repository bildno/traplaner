package com.project.traplaner.member.repository;

import com.project.traplaner.entity.Favorite;
import com.project.traplaner.entity.Member;
import com.project.traplaner.entity.Travel;
import com.project.traplaner.entity.TravelBoard;
import com.project.traplaner.travelBoard.repository.FavoriteRepository;
import com.project.traplaner.travelBoard.repository.TravelBoardRepository;
import com.project.traplaner.travelplan.repository.TravelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.project.traplaner.entity.Member.builder;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TravelRepository travelRepository;

    @Autowired
    TravelBoardRepository travelBoardRepository;

    @Autowired
    FavoriteRepository favoriteMapperRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;


    @Test
    @DisplayName("Test DB 테이블 데이터 생성")
    void makeTestTableData() {

        int subscriberCount = 100;
        int randomPara = subscriberCount - 1;
        int favoriteCount = subscriberCount;
        int randomFavoritePara = (subscriberCount / 10) - 1;

        for (int i = 1; i <= subscriberCount; i++) {
            Member m = builder()
                    .nickName("테스트" + i)
                    .email("test" + i + "@abc.net")
                    .password(passwordEncoder.encode("1234@qwer"))
                    .loginMethod(Member.LoginMethod.COMMON)
                    .profileImg("47a3e6ea4e1d4377ab87d07c85e1b124.png")
                    .build();
            Member member = memberRepository.save(m);
        }

        for (int i = 1; i <= subscriberCount; i++) {
            long randNum = (long) (Math.random() * randomPara) + 1;
            long tmpMemberId = randNum;
            long tmpMemberNickNameId = randNum;

            LocalDateTime tmpStartDate = LocalDateTime.now();
            LocalDateTime tmpEndDate = LocalDateTime.now().plusDays(3);
            LocalDateTime tmpUpdatedDate =
                    LocalDateTime.now().plusDays(3 + (int) (Math.random() * randomPara) + 1);

            String fileName = String.format("0425d9dc324e4d2a822b8ac905123b%02d.jpg", (int) (Math.random() * 9) + 1);
            Member member = memberRepository.findById(tmpMemberId).orElseThrow(
                        ()->new EntityNotFoundException("Member not found")
            );
            Travel travel = Travel.builder()
                    .member(member)
                    .title("여행-" + i)
                    .startDate(tmpStartDate)
                    .endDate(tmpEndDate)
                    .share(true)
//                    .travelImg("\\0425d9dc324e4d2a822b8ac905123b9"
//                            + (int) (Math.random() * 9) + 1
//                            +"1.jpg")
                    .travelImg(fileName)
                    .build();
            travelRepository.save(travel);

            TravelBoard travelBoard = TravelBoard.builder()
                    .travel(travel)
                    .memberNickName("테스트" + tmpMemberNickNameId)
                    .writeDate(tmpEndDate)
                    .content("여행-" + i + " 좋았음. !!!!!!!!!!!!")
                    .build();
            travelBoardRepository.save(travelBoard);
        }

        for (int i = 1; i <= favoriteCount; i++) {
            long tmpFavoriteMemberId = (long) (Math.random() * randomFavoritePara) + 1;
            long tmpFavoriteTravelBoardId = (long) (Math.random() * randomFavoritePara) + 1;
            Member member = memberRepository.findById(tmpFavoriteMemberId).orElseThrow(
                    ()->new EntityNotFoundException("Member not found")
            );
            TravelBoard travelBoard = travelBoardRepository.findById(tmpFavoriteTravelBoardId).orElseThrow(
                    ()->new EntityNotFoundException("TravelBoard not found")
            );
            Favorite favorite = Favorite.builder()
                    .member(member)
                    .travelBoard(travelBoard)
                    .build();

            favoriteRepository.save(favorite);
        }

    }
}