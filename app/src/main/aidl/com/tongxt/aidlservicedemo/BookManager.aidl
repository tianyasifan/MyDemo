// BookManager.aidl
package com.tongxt.aidlservicedemo;
//引入了自定义的类，需要显示导入包名
import com.tongxt.aidlservicedemo.Book;
// Declare any non-default types here with import statements

interface BookManager {
    List<Book> getBooks();
    Book getBook(String name);
    int getBookCount();
    //传参时除了Java基本类型以及String，CharSequence之外的类型
    //都需要在前面加上定向tag，具体加什么量需而定
    void addBook(in Book book);
}
