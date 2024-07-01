package com.kos.tr.news.board.entity;

import com.kos.tr.news.board.dto.PostDTO;
import com.kos.tr.news.common.enums.UseType;
import com.kos.tr.news.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import java.time.LocalTime;

@Getter
@Builder
public class Post {

    private String id;
    private String subject;
    private String memo;
    private String file;
    private int viewCount;
    private UseType deleteYn;
    private String insertDate;
    private String updateDate;

    private Member member;

    public void addViewCount(){
        this.viewCount++;
    }

    public void updateContents(PostDTO dto){
        this.subject = dto.getSubject();
        this.memo = dto.getMemo();
        this.file = dto.getFile();
        this.updateDate = LocalTime.now().toString();
    }

    /**
     * 사용하려면 entity에 Setter 필요
     * 단순 필드 복사시, 리플렉션 사용해 성능 떨어질 수 있음
     **/
    public void updateByBeanUtils(PostDTO dto){
        BeanUtils.copyProperties(dto, this);
    }

    /**
     * 필드 이름이 다르 거나, 합치는 변환 로직 필요시 좀더 복잡한 맵핑 기능
     * */
    public void updateByModelMapper(PostDTO dto){
        ModelMapper mapper = new ModelMapper();
        mapper.map(dto, this);
    }



    public void unused(){
        this.deleteYn = UseType.N;
    }

    public static Post newPost(PostDTO dto, Member writer){
        return Post.builder()
                .member(writer)
                .subject(dto.getSubject())
                .memo(dto.getMemo())
                .memo(dto.getMemo())
                .deleteYn(UseType.Y)
                .insertDate(LocalTime.now().toString())
                .updateDate(LocalTime.now().toString())
                .build();
    }
}
