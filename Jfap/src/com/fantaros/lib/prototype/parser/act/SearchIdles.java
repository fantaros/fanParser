package com.fantaros.lib.prototype.parser.act;

import java.util.ArrayList;
import java.util.List;


public class SearchIdles {
  private List<Idle> idles;
  
  public static SearchIdles create(String selector){
    return new SearchIdles(selector);
  }
  
  private SearchIdles(String selector){
    parse(selector);
  }
  public void parse(String selector) {
    idles = new ArrayList<Idle>();
    if(selector!=null && selector.trim().length()>0){
      if(selector.indexOf(",")<0){
        idles.add(IdleWrapper.create(selector));
      } else {
        String[] sps = Tag.removeEmpty(selector.split(","));
        for(String source : sps){
          idles.add(IdleWrapper.create(source));
        }
      }
    }
  }
  
  public boolean test(Tag tag){
    if(idles == null || idles.isEmpty()){
      return true;
    } else {
      boolean test = false;
      for(Idle idle : idles){
        test = test | idle.test(tag);
        if(test)
          break;
      }
      return test;
    }
  }
}
