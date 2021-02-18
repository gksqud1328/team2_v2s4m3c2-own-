package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

public interface NoticeDAOInter {

  /**
   * ���
   * @param noticeVO
   * @return
   */
  public int create(NoticeVO noticeVO);

  /**
   * ���
   * @param noticeVO
   * @return
   */
  public List<NoticeVO> list();

  /**
   * ��ȸ, ���� ��, ���� ��
   * @param noticeno
   * @return
   */
  public NoticeVO read(int noticeno);

  /**
   * ���� ó��
   * <xmp>
   *   <update id="update" parameterType="NoticeVO"> 
   * </xmp>
   * @param noticeVO
   * @return ó���� ���ڵ� ����
   */
  public int update(NoticeVO noticeVO);
   
  /**
   * ����
   * <xmp>
   *   <update id="update" parameterType="NoticeVO"> 
   * </xmp>
   * @param noticeVO
   * @return ó���� ���ڵ� ����
   */
  public int delete(int noticeno);
    
  /**
   * �н����� �˻�
   * @param hashMap
   * @return
   */
  public int passwd_check(HashMap hashMap);
    
  /**
   * ��� ���� ����
   * <xmp>
   * <update id="update_noticeno_up" parameterType="int">
   * </xmp>
   * @param noticeno
   * @return ó���� ���ڵ� ����
   */
  public int update_noticeno_up(int noticeno);
  
  /**
   * ��� ���� ����
   * <xmp>
   * <update id="update_noticeno_down" parameterType="int">
   * </xmp>
   * @param noticeno
   * @return ó���� ���ڵ� ����
   */
  public int update_noticeno_down(int noticeno);
  
  /**
   * ��� ��� ����
   * <xmp>
   * <update id="update_visible" parameterType="int">
   * </xmp>
   * @param noticeno
   * @return ó���� ���ڵ� ����
   */
  public int update_visible(NoticeVO noticeVO);
  
  /**
   * ��ȸ�� ����
   * <xmp>
   * <update id="update_count" parameterType="NoticeVO">
   * </xmp>
   * @param count
   * @return ó���� ���ڵ� ����
   */
  public int update_count(int noticeno);
}
