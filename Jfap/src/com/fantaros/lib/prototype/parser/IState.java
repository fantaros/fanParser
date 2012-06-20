package com.fantaros.lib.prototype.parser;

import java.util.Collection;

public interface IState extends ICommand, IPrototype {

  int getStateCode();

  Collection<Character> getTokens();

  Collection<Character> getITokens();

  void parsing(char token);

  IPrototype result();
}
