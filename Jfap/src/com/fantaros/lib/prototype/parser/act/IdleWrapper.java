package com.fantaros.lib.prototype.parser.act;

public class IdleWrapper implements Idle {

  public IdleWrapper(String source) {
    parse(source);
  }

  public void parse(String source) {
    SOURCE = source;
    if (SOURCE != null && !SOURCE.trim().equals("")) {
      TYPE = SOURCE.charAt(0);
      if (TYPE == '-') {
        PREFIX = false;
        TYPE = SOURCE.charAt(1);
        EQUATION = SOURCE.substring(2, SOURCE.length());
      } else {
        PREFIX = true;
        EQUATION = SOURCE.substring(1, SOURCE.length());
      }
      String[] sps = Tag.removeEmpty(EQUATION.split(" "));
      if (sps != null && sps.length == 3) {
        NAME = sps[0];
        OPERATER = sps[1];
        VALUE = sps[2];
        READY = true;
      } else {
        READY = false;
      }
    }
  }

  public static Idle create(String source) {
    return new IdleWrapper(source);
  }

  public char    TYPE;
  public boolean PREFIX;
  public String  EQUATION;
  public String  OPERATER;
  public String  NAME;
  public String  VALUE;
  public String  SOURCE;
  public boolean READY;

  public boolean test(Tag tag) {
    if (READY) {
      boolean r;
      switch (TYPE) {
        case 'a':
          r = testA(tag);
          break;
        case 'n':
          r = testN(tag);
          break;
        case 'i':
          r = testI(tag);
          break;
        case 'o':
          r = testO(tag);
          break;
        case 'p':
          r = testP(tag);
          break;
        case 'c':
          r = testC(tag);
          break;
        default:
          r = false;
          break;
      }
      return PREFIX?r:!r;
    } else {
      return false;
    }
  }

  private boolean testC(Tag tag) {
    boolean test = false ;
    if(OPERATER.equals("=")){
      for(Tag tag2 : tag.getChildrens()){
        if(tag2.getTagName().equals(VALUE)){
          test = true;
          break;
        }
      }
      return test;
    } else if(OPERATER.equals(">")){
      for(Tag tag2 : tag.getChildrens()){
        if(tag2.getTagName().indexOf(VALUE)>-1){
          test = true;
          break;
        }
      }
      return test;
    } else if(OPERATER.equals("<")){
      for(Tag tag2 : tag.getChildrens()){
        if(VALUE.indexOf(tag2.getTagName())>-1){
          test = true;
          break;
        }
      }
      return test;
    } else {
      return false;
    }
  }

  private boolean testP(Tag tag) {
    boolean test = false ;
    if(OPERATER.equals("=")){
      if(tag.getParent().getTagName().equals(VALUE)){
        test = true;
      }
      return test;
    } else if(OPERATER.equals(">")){
        if(tag.getParent().getTagName().indexOf(VALUE)>-1){
          test = true;
        }
      return test;
    } else if(OPERATER.equals("<")){
        if(VALUE.indexOf(tag.getParent().getTagName())>-1){
          test = true;
        }
      return test;
    } else {
      return false;
    }
  }

  private boolean testO(Tag tag) {
    boolean test = false ;
    if(OPERATER.equals("=")){
      if(tag.getSourceHTML().equals(VALUE)){
        test = true;
      }
      return test;
    } else if(OPERATER.equals(">")){
        if(tag.getSourceHTML().indexOf(VALUE)>-1){
          test = true;
        }
      return test;
    } else if(OPERATER.equals("<")){
        if(VALUE.indexOf(tag.getSourceHTML())>-1){
          test = true;
        }
      return test;
    } else {
      return false;
    }
  }

  private boolean testI(Tag tag) {
    boolean test = false ;
    if(OPERATER.equals("=")){
      if(tag.getInnerHTML().equals(VALUE)){
        test = true;
      }
      return test;
    } else if(OPERATER.equals(">")){
        if(tag.getInnerHTML().indexOf(VALUE)>-1){
          test = true;
        }
      return test;
    } else if(OPERATER.equals("<")){
        if(VALUE.indexOf(tag.getInnerHTML())>-1){
          test = true;
        }
      return test;
    } else {
      return false;
    }
  }

  private boolean testN(Tag tag) {
    boolean test = false ;
    if(OPERATER.equals("=")){
      if(tag.getTagName().equals(VALUE)){
        test = true;
      }
      return test;
    } else if(OPERATER.equals(">")){
        if(tag.getTagName().indexOf(VALUE)>-1){
          test = true;
        }
      return test;
    } else if(OPERATER.equals("<")){
        if(VALUE.indexOf(tag.getTagName())>-1){
          test = true;
        }
      return test;
    } else {
      return false;
    }
  }

  private boolean testA(Tag tag) {
    if(OPERATER.equals("=")){
      if(tag.getAttributes().containsKey(NAME) && tag.getAttributes().get(NAME).equals(VALUE)){
        return true;
      } else {
        return false;
      }
    } else if(OPERATER.equals(">")){
      if(tag.getAttributes().containsKey(NAME) && tag.getAttributes().get(NAME).indexOf(VALUE)>-1){
        return true;
      } else {
        return false;
      }
    
    } else if(OPERATER.equals("<")){
      if(tag.getAttributes().containsKey(NAME) && VALUE.indexOf(tag.getAttributes().get(NAME))>-1){
        return true;
      } else {
        return false;
      }
    
    } else {
      return false;
    }
  }

}
