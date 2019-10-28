package com.team.house.util;
//分页工具类
public class PageUtil {
 public   Integer page;//接受页码
 public Integer rows;//页大小

 public Integer getPage() {
  return page;
 }

 public void setPage(Integer page) {
  this.page = page;
 }

 public Integer getRows() {
  return rows;
 }

 public void setRows(Integer rows) {
  this.rows = rows;
 }

 @Override
 public String toString() {
  return "PageUtil{" +
   "page=" + page +
   ", rows=" + rows +
   '}';
 }
}
