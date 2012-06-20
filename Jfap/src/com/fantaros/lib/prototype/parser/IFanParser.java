package com.fantaros.lib.prototype.parser;

public interface IFanParser extends ICommand,IPrototype {

  IState getState();

  void setSelector(ISelector selector);
  
  ITarget getCollection();
}
