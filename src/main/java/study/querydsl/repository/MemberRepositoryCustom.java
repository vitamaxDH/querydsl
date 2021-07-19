package study.querydsl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;

import java.util.List;

// 기본적으로 Custom 을 만들고 Impl 에다가 구현하는것이 조 ㅎ다
public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);
    Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable);
    Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable);

}
