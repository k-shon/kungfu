package top.annokshon.kungfu.mapper;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import top.annokshon.kungfu.entity.Picture;

import java.util.List;
import java.util.Optional;

public interface PictureMapper extends JpaRepository<Picture,Integer> {
    @Override
    List<Picture> findAll();

    @Override
    List<Picture> findAll(Sort sort);

    @Override
    <S extends Picture> S save(S s);

    @Override
    Optional<Picture> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Picture picture);
}
