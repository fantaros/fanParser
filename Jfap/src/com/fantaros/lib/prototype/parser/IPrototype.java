package com.fantaros.lib.prototype.parser;

import java.util.Map;


public interface IPrototype extends Map<String,Object>{
  Object get(String pName);
  void set(String pName,Object pValue);
}
