package dev.mvc.newsgrp;

import java.util.List;

public interface NewsgrpDAOInter {
  
   /**
   * <Xmp>
   * 뉴스 그룹 등록
   * <insert id="create" parameterType="NewsgrpVO"> 
   * <Xmp>
   * @param newsgrpVO
   * @return
   */
  public int create(NewsgrpVO newsgrpVO);
  
   /**
   * 그룹 목록
   * <Xmp>
   * <select id="list_newsgrp_no_asc" resultType="NewsgrpVO">
   * <Xmp>
   * @return 레코드 목록
   */
  public List<NewsgrpVO> list_newsgrp_no_asc();
  
   /**
   * 조회
   * <Xmp>
   * <select id="read" resultType="NewsgrpVO" parameterType="int">
   * <Xmp>
   * @param newsgrp_no
   * @return
   */
  public NewsgrpVO read(int newsgrp_no);
  
   /**
   * 수정처리
   * <xmp>
   * <update id="update" parameterType="NewsgrpVO"> 
   * @param newsgrpVO
   * @return
   */
  public int update(NewsgrpVO newsgrpVO);
  
   /**
   * 삭제 처리
   * <Xmp>
   * <delete id="delete" parameterType="int">
   * <Xmp>
   * @param newsgrp_no
   * @return
   */
  public int delete(int newsgrp_no);
  
   /**
   * 그룹 순서별 목록  
   * <xmp>
   * <select id="list_seqno_asc" resultType="NewsgrpVO">
   * <xmp>
   * @return
   */
  public List<NewsgrpVO> list_newsgrp_seqno_asc();
  
   /**
   * 출력 순서 상향
   * @param newsgrp_no
   * @return
   */
  public int update_newsgrp_seqno_up(int newsgrp_no);
  
   /**
   * 출력 순서 하향
   * @param newsgrp_no
   * @return
   */
  public int update_newsgrp_seqno_down(int newsgrp_no);
  
  /**
   * 글 수 증가
   * @return
   */
  public int increaseCnt(int newsgrp_no);    

  /**
   * 글 수 감소
   * @return
   */
  public int decreaseCnt(int newsgrp_no);
}
