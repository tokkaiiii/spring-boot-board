package heo.boot.my.dto;

import heo.boot.my.domain.Board;
import org.springframework.data.domain.Page;

public class BoardListResult {

  private Page<Board> list;
  private int page;
  private int size;
  private long totalCount;
  private long totalPageCount;

  public BoardListResult(Page<Board> list, int page, int size, long totalCount, long totalPageCount) {
    this.list = list;
    this.page = page;
    this.size = size;
    this.totalCount = totalCount;
    this.totalPageCount = totalPageCount;
  }

  private long calTotalPageCount() {
    long tpc = totalCount / size;
    if (totalCount % size != 0) {
      tpc++;
    }
    return tpc;
  }

  public Page<Board> getList() {
    return list;
  }

  public void setList(Page<Board> list) {
    this.list = list;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(long totalCount) {
    this.totalCount = totalCount;
  }

  public long getTotalPageCount() {
    return totalPageCount;
  }

  public void setTotalPageCount(long totalPageCount) {
    this.totalPageCount = totalPageCount;
  }
}
