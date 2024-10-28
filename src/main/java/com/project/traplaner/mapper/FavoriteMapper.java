package com.project.traplaner.mapper;


import com.project.traplaner.entity.Favorite;
import com.project.traplaner.mypage.dto.response.FavoriteListResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavoriteMapper {


    List<FavoriteListResponseDTO> favorite_List(int memberId);

    // 10/28 by jhjeong
    void save(Favorite favorite);


//public interface FavoriteMapper {

}
