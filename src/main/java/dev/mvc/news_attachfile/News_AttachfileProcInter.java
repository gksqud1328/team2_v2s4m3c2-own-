package dev.mvc.news_attachfile;

import java.util.List;

public interface News_AttachfileProcInter {
  
  /**
   * ���� ���  
   * @param News_AttachfileVO
   * @return
   */
  public int create(News_AttachfileVO news_AttachfileVO);
  
   /**
   * ��ü �̹��� ���
   * @return
   */
  public List<News_AttachfileVO> list();
  
   /**
   * ��ȸ
   * @param news_attachfile_no
   * @return
   */
  public News_AttachfileVO read(int news_attachfile_no);
  
   /**
   * news_no�� ���� ���� ���
   * @param news_no
   * @return
   */
  public List<News_AttachfileVO> list_by_news_no(int news_no);
  
   /**
   * ����
   * @param news_attachfile_no
   * @return
   */
  public int delete(int news_attachfile_no);
  
  /**
   * news_no�� ����
   * @param news_no
   * @return
   */
  public int delete_by_news_no(int news_no);
  
   /**
   * news_no�� ī��Ʈ
   * @param news_no
   * @return
   */
  public int count_by_news_no(int news_no);

  

}
