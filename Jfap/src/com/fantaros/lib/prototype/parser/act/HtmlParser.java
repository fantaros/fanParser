package com.fantaros.lib.prototype.parser.act;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HtmlParser {
  public static Tag parseHtml(String source) {
    Tag root = Tag.createTag("root");
    String html = source.replace("\r\n", "\n").replace("\n", "");
    html = html.replace("<br>", "<br/>");
    Stack<Tag> stack = new Stack<Tag>();
    stack.push(root);
    int sof = 0, eof = html.length() - 1, oldeof = 0;
    String tempc;
    Tag temptag = null;
    while (sof < html.length()) {
      sof = html.indexOf('<', sof);
      if (sof < 0) break;
      eof = html.indexOf('>', sof);
      tempc = html.substring(sof, eof+1);
      if (tempc.length() > 2) {
        if (tempc.charAt(1) == '/') {
          if (stack.size() > 0)
            stack.pop();
          else break;
          if (oldeof + 1 != sof) {
            if (temptag != null) temptag.setInnerHTML(html.substring(oldeof + 1, sof));
          }
          oldeof = eof;
          sof = eof;
        } else if (tempc.charAt(1) == '!') {
          temptag = Tag.createTag(stack.peek(), tempc);
          oldeof = eof;
          sof = eof;
        } else {
          if (tempc.length() >2 && tempc.substring(tempc.length() - 2, tempc.length() - 1) == "/>") {
            temptag = Tag.createTag(stack.peek(), tempc);
            oldeof = eof;
            sof = eof;
          } else {
            temptag = Tag.createTag(stack.peek(), tempc);
            stack.push(temptag);
            oldeof = eof;
            sof = eof;
          }
        }
      }
    }
    return root;
  }
  
  public static void printTag(Tag root){
    System.out.print("start ...");
    System.out.println(root.getTagName());
    for(Tag c : root.getChildrens()){
      printTag(c);
    }
    System.out.print("end ...");
    System.out.println(root.getTagName());
  }

  public static void main(String[] args) {
    File file = new File("D:\\test.html");
    InputStream in;
    try {
      in = new FileInputStream(file);
      int temp;
      List<Byte> buffer = new ArrayList<Byte>();
      while ((temp = in.read()) != -1) {
        buffer.add(new Byte((byte) temp));
      }
      byte[] bytes = new byte[buffer.size()];
      int count = 0;
      for(Byte b : buffer){
        bytes[count] = b.byteValue();
        count++;
      }
      String htmlsource = new String(bytes,"UTF-8");
      Tag root = HtmlParser.parseHtml(htmlsource);
      Tag table = root.getChild(0).getChild(1).getChild(0);
      System.out.println(table.getChildrens());
      //Tag tbody =  root.getChildrens().get(0).getChildrens().get(1).getChildrens().get(0).getChildrens().get(0);
      //System.out.println(tbody.getChildrens().size());
      //List<Tag> tags =tbody.search("n n > tr,c c > td");
      //for(Tag tag : tags){
        //System.out.println(tag);
      //}
      //printTag(root);
      in.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
