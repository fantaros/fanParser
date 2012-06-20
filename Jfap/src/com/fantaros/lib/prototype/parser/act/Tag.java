package com.fantaros.lib.prototype.parser.act;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tag {

  public static Tag createTag(String name) {
    return new Tag(name);
  }

  public static Tag createTag(Tag parent, String source) {
    return new Tag(parent, source);
  }

  private String              tagName;
  private Map<String, String> attributes;
  private Tags           childrens;
  private Tag                 parent;
  private String              sourceHTML;
  private String              innerHTML;

  public Tag getParent() {
    return parent;
  }

  public void setParent(Tag parent) {
    this.parent = parent;
  }

  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  public Map<String, String> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, String> attributes) {
    this.attributes = attributes;
  }

  public Tags getChildrens() {
    return childrens;
  }
  
  public Tag getChild(int index){
    return childrens.get(index);
  }

  public void setChildrens(Tags childrens) {
    this.childrens = childrens;
  }

  public String getSourceHTML() {
    return sourceHTML;
  }

  public void setSourceHTML(String source) {
    this.sourceHTML = source;
  }

  public String getInnerHTML() {
    return innerHTML;
  }

  public void setInnerHTML(String innerHTML) {
    this.innerHTML = innerHTML;
  }

  public Tag(String name) {
    this.tagName = name;
    this.attributes = new HashMap<String, String>();
    this.childrens = new Tags();
    this.parent = null;
    this.sourceHTML = "";
  }

  public Tag(Tag parent, String source) {
    this.tagName = "";
    this.attributes = new HashMap<String, String>();
    this.childrens = new Tags();
    this.parent = parent;
    parse(source);
    this.parent.addChildTag(this);
  }

  public Tags search(String selector) {
    Tags tags = new Tags();
    SearchIdles testor = SearchIdles.create(selector);
    for (Tag tag : this.getChildrens()) {
      if (testor.test(tag)) {
        tags.add(tag);
      }
    }
    return tags;
  }
  
  public Tags searchThrough(String selector) {
    Tags tags = new Tags();
    SearchIdles testor = SearchIdles.create(selector);
    searchThrough(tags,testor,this);
    return tags;
  }

  private void searchThrough(Tags tags, SearchIdles testor, Tag tag) {
    for (Tag t : tag.getChildrens()) {
      if (testor.test(t)) {
        tags.add(t);
      }
    }
  }

  public static String[] removeEmpty(String[] src) {
    if (src == null)
      return new String[0];
    else {
      List<String> t = new ArrayList<String>();
      for (String s : src) {
        if (s != null && !s.equals("")) {
          t.add(s);
        }
      }
      if (t.isEmpty()) {
        return new String[0];
      } else {
        return t.toArray(new String[t.size()]);
      }
    }
  }

  public void parse(String source) {
    this.sourceHTML = source;
    String pre = source.replace("<", " ").replace(">", "");
    String[] rs = pre.split(" ");
    String[] sps = removeEmpty(rs);
    if (sps.length > 0) {
      if (sps[0].charAt(0) == '!') {
        this.tagName = "!";
        this.attributes.put("tag-type", sps[0].substring(1, sps[0].length() - 1).toLowerCase());
        this.innerHTML = "";
      } else {
        this.tagName = sps[0].toLowerCase();
        this.innerHTML = "";
      }
      if (sps.length > 1) {
        String[] avp;
        for (int i = 1; i < sps.length; ++i) {
          avp = sps[i].replace("\"", "=").replace("'", "=").split("=");
          if (avp.length > 0) {
            if (avp.length == 1) {
              this.attributes.put(avp[0], "true");
            } else if (avp.length == 2) {
              this.attributes.put(avp[0], avp[1]);
            } else {
              StringBuffer sb = new StringBuffer();
              for (int j = 1; j < avp.length; ++j) {
                if (!avp[j].equals("")) {
                  sb.append(avp[j]);
                }
              }
              this.attributes.put(avp[0], sb.toString());
            }
          }
        }
      }
    }
  }

  @Override
  public String toString() {
    return (new StringBuffer()).append(this.getTagName()).append("(")
      .append(this.getParent().getTagName()).append("):")
      .append(this.getSourceHTML()).toString();
  }

  public boolean hasChildren() {
    if (this.childrens == null) {
      return false;
    } else {
      return this.childrens.isEmpty();
    }
  }

  public void addAttribute(String attrName, String attrValue) {
    this.attributes.put(attrName, attrValue);
  }

  public void addChildTag(Tag child) {
    this.childrens.add(child);
  }
}
