package top.annokshon.kungfu.mapper;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import top.annokshon.kungfu.entity.DojoMember;

import java.util.List;
import java.util.Optional;

public interface DojoMemberMapper extends JpaRepository<DojoMember,Integer> {
    @Override
    List<DojoMember> findAll();

    @Override
    List<DojoMember> findAll(Sort sort);

    @Override
    <S extends DojoMember> S save(S s);

    @Override
    Optional<DojoMember> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void deleteAll();
}
