package dev.mvc.news;

import java.util.HashMap;
import java.util.List;

public interface NewsDAOInter {

  /**
   * ���
   * @param newsVO
   * @return 
   */
  public int create(NewsVO newsVO);
   
  /**
   * ��� ���� �׷��� ��ϵ� �۸��
   * @return
   */
  public List<NewsVO> list_all();
  
  /**
   * Ư�� ���� �׷��� ��ϵ� �۸��(�����)
   * @return
   */
  public List<NewsVO> list(int newsgrp_no);
  
  /**
   * Ư�� ���� �׷��� ��ϵ� �۸��(�׸�����)
   * @return
   */
  public List<NewsVO> list_by_newsgrp_no(int newsgrp_no);
 
  /**
   * ��ȸ
   * @param news_no
   * @return
   */
  public NewsVO read(int news_no);
  
  /**
   * ������ ��ȸ
   * @param news_no
   * @return
   */
  public NewsVO read_update(int news_no);
  
  /**
   * ���� ó��
   * @param newsVO
   * @return
   */
  public int update(NewsVO newsVO);
  
  /**
   * ����
   * @param newsVO
   * @return
   */
  public int delete(int news_no);
  
  /**
   * �н����� �˻�
   * @param hashMap
   * @return
   */
  public int passwd_check(HashMap<String, Object>hashMap);
  
  /**
   * �̹��� ����
   * @param newsVO
   * @return
   */
  public int update_img(NewsVO newsVO);

  /**
   * ��ȸ�� ����
   * <xmp>
   * <update id="update_count" parameterType="NoticeVO">
   * </xmp>
   * @param count
   * @return ó���� ���ڵ� ����
   */
  public int update_count(int news_no);
}
 