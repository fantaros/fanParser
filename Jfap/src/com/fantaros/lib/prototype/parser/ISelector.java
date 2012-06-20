package com.fantaros.lib.prototype.parser;

public interface ISelector extends IPrototype, ICommand {

  boolean hasChanged(IState current, char nextToken);

  void parseNextToken(IState current, char nextToken);
}
