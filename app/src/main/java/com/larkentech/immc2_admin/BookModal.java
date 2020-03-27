package com.larkentech.immc2_admin;


import android.os.Parcel;
import android.os.Parcelable;

public class BookModal implements Parcelable {
    private String BookName;
    private String BookPrice160Pages;
    private String BookPrice200Pages;
    private String BookPrice240Pages;
    private String BookDesc;
    private String BookImage;
    private BookImages BookImages;
    private String BookDesigner;
    private String BookCategory;
    private String BookID;
    private String BookSubCategory;

    public BookModal() {
    }

    public BookModal(String bookName, String bookPrice160Pages, String bookPrice200Pages, String bookPrice240Pages, String bookDesc, String bookImage, com.larkentech.immc2_admin.BookImages bookImages, String bookDesigner, String bookCategory, String bookID, String bookSubCategory) {
        BookName = bookName;
        BookPrice160Pages = bookPrice160Pages;
        BookPrice200Pages = bookPrice200Pages;
        BookPrice240Pages = bookPrice240Pages;
        BookDesc = bookDesc;
        BookImage = bookImage;
        BookImages = bookImages;
        BookDesigner = bookDesigner;
        BookCategory = bookCategory;
        BookID = bookID;
        BookSubCategory = bookSubCategory;
    }

    public com.larkentech.immc2_admin.BookImages getBookImages() {
        return BookImages;
    }

    public void setBookImages(com.larkentech.immc2_admin.BookImages bookImages) {
        BookImages = bookImages;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookPrice160Pages() {
        return BookPrice160Pages;
    }

    public void setBookPrice160Pages(String bookPrice160Pages) {
        BookPrice160Pages = bookPrice160Pages;
    }

    public String getBookPrice200Pages() {
        return BookPrice200Pages;
    }

    public void setBookPrice200Pages(String bookPrice200Pages) {
        BookPrice200Pages = bookPrice200Pages;
    }

    public String getBookPrice240Pages() {
        return BookPrice240Pages;
    }

    public void setBookPrice240Pages(String bookPrice240Pages) {
        BookPrice240Pages = bookPrice240Pages;
    }

    public String getBookDesc() {
        return BookDesc;
    }

    public void setBookDesc(String bookDesc) {
        BookDesc = bookDesc;
    }

    public String getBookImage() {
        return BookImage;
    }

    public void setBookImage(String bookImage) {
        BookImage = bookImage;
    }

    public String getBookDesigner() {
        return BookDesigner;
    }

    public void setBookDesigner(String bookDesigner) {
        BookDesigner = bookDesigner;
    }

    public String getBookCategory() {
        return BookCategory;
    }

    public void setBookCategory(String bookCategory) {
        BookCategory = bookCategory;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String bookId) {
        BookID = bookId;
    }

    public String getBookSubCategory() {
        return BookSubCategory;
    }

    public void setBookSubCategory(String bookSubCategory) {
        BookSubCategory = bookSubCategory;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
