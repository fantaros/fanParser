package com.fantaros.lib.prototype.parser.html;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.fantaros.lib.prototype.parser.ITarget;


public abstract class HtmlTarget implements ITarget {
  private Map<String,Object> basement;
  
  public HtmlTarget(){
    basement = new HashMap<String,Object>();
  }
  
  @Override
  public Object get(String pName) {
    return basement.get(pName);
  }

  @Override
  public void set(String pName, Object pValue) {
   basement.put(pName,pValue);
  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean containsKey(Object arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean containsValue(Object arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Set<java.util.Map.Entry<String, Object>> entrySet() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object get(Object arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Set<String> keySet() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object put(String arg0, Object arg1) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void putAll(Map<? extends String, ? extends Object> arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public Object remove(Object arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Collection<Object> values() {
    // TODO Auto-generated method stub
    return null;
  }

}
