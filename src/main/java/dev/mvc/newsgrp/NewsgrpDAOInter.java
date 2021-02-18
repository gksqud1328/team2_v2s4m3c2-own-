package dev.mvc.newsgrp;

import java.util.List;

public interface NewsgrpDAOInter {
  
   /**
   * <Xmp>
   * ���� �׷� ���
   * <insert id="create" parameterType="NewsgrpVO"> 
   * <Xmp>
   * @param newsgrpVO
   * @return
   */
  public int create(NewsgrpVO newsgrpVO);
  
   /**
   * �׷� ���
   * <Xmp>
   * <select id="list_newsgrp_no_asc" resultType="NewsgrpVO">
   * <Xmp>
   * @return ���ڵ� ���
   */
  public List<NewsgrpVO> list_newsgrp_no_asc();
  
   /**
   * ��ȸ
   * <Xmp>
   * <select id="read" resultType="NewsgrpVO" parameterType="int">
   * <Xmp>
   * @param newsgrp_no
   * @return
   */
  public NewsgrpVO read(int newsgrp_no);
  
   /**
   * ����ó��
   * <xmp>
   * <update id="update" parameterType="NewsgrpVO"> 
   * @param newsgrpVO
   * @return
   */
  public int update(NewsgrpVO newsgrpVO);
  
   /**
   * ���� ó��
   * <Xmp>
   * <delete id="delete" parameterType="int">
   * <Xmp>
   * @param newsgrp_no
   * @return
   */
  public int delete(int newsgrp_no);
  
   /**
   * �׷� ������ ���  
   * <xmp>
   * <select id="list_seqno_asc" resultType="NewsgrpVO">
   * <xmp>
   * @return
   */
  public List<NewsgrpVO> list_newsgrp_seqno_asc();
  
   /**
   * ��� ���� ����
   * @param newsgrp_no
   * @return
   */
  public int update_newsgrp_seqno_up(int newsgrp_no);
  
   /**
   * ��� ���� ����
   * @param newsgrp_no
   * @return
   */
  public int update_newsgrp_seqno_down(int newsgrp_no);
  
  /**
   * �� �� ����
   * @return
   */
  public int increaseCnt(int newsgrp_no);    

  /**
   * �� �� ����
   * @return
   */
  public int decreaseCnt(int newsgrp_no);
}
