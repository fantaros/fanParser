package com.fantaros.lib.prototype.parser;

public interface ICommand {
  ITarget execute(Object[] param);
  ITarget execute(String functionName,Object ... params);
}
