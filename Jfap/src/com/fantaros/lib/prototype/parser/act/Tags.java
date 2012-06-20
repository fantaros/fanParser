package com.fantaros.lib.prototype.parser.act;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class Tags implements List<Tag> {

  public static Tags convert(List<Tag> tags){
    Tags ts = new Tags();
    for(Tag t : tags){
      ts.add(t);
    }
    return ts;
  }
  
  private List<Tag> otags;
  
  public Tags(){
    otags = new ArrayList<Tag>();
  }
  
  public Tags(List<Tag> tags){
    otags = tags;
  }
  
  public Tags(Tag tag){
    otags = new ArrayList<Tag>();
    otags.add(tag);
  }
  
  public Tags getByTagName(String tagName){
    Tags tags = new Tags();
    if(!this.isEmpty()){
      for(Tag tag : this){
        if(tag.getTagName().equals(tagName)){
          tags.add(tag);
        }
      }
    }
    return tags;
  }
  
  public Tags search(String selector){
    Tags tags = new Tags();
    SearchIdles testor = SearchIdles.create(selector);
    for (Tag tag : this) {
      if (testor.test(tag)) {
        tags.add(tag);
      }
    }
    return tags;
  }
  
  public Tags searchThrough(String selector){
    Tags tags = new Tags();
    SearchIdles testor = SearchIdles.create(selector);
    for(Tag tag : this){
      searchThrough(tags,testor,tag);
    }
    return tags;
  }
  
  private void searchThrough(Tags tags, SearchIdles testor, Tag tag) {
    for (Tag t : tag.getChildrens()) {
      if (testor.test(t)) {
        tags.add(t);
      }
    }
  }
  
  public boolean add(Tag o) {
    return otags.add(o);
  }

  public void add(int index, Tag element) {
    otags.add(index,element);
  }

  public boolean addAll(Collection<? extends Tag> c) {
    return otags.addAll(c);
  }

  public boolean addAll(int index, Collection<? extends Tag> c) {
    return  otags.addAll(index,c);
  }

  public void clear() {
    otags.clear();
  }

  public boolean contains(Object o) {
    return otags.contains(o);
  }

  public boolean containsAll(Collection<?> c) {
    return otags.containsAll(c);
  }

  public Tag get(int index) {
    return otags.get(index);
  }

  public int indexOf(Object o) {
    return otags.indexOf(o);
  }

  public boolean isEmpty() {
    return otags.isEmpty();
  }

  public Iterator<Tag> iterator() {
    return otags.iterator();
  }

  public int lastIndexOf(Object o) {
    return otags.lastIndexOf(o);
  }

  public ListIterator<Tag> listIterator() {
    return otags.listIterator();
  }

  public ListIterator<Tag> listIterator(int index) {
    return otags.listIterator(index);
  }

  public boolean remove(Object o) {
    return otags.remove(o);
  }

  public Tag remove(int index) {
    return otags.remove(index);
  }

  public boolean removeAll(Collection<?> c) {
    return otags.removeAll(c);
  }

  public boolean retainAll(Collection<?> c) {
    return otags.retainAll(c);
  }

  public Tag set(int index, Tag element) {
    return otags.set(index,element);
  }

  public int size() {
    return otags.size();
  }

  public List<Tag> subList(int fromIndex, int toIndex) {
    return otags.subList(fromIndex,toIndex);
  }

  public Object[] toArray() {
    return otags.toArray();
  }

  public <T> T[] toArray(T[] a) {
    return otags.toArray(a);
  }
  
  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer();
    for(Tag t : this){
      buffer.append(t.toString()).append("\r\n");
    }
    return buffer.toString();
  }

}
