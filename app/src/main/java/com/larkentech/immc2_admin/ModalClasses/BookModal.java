package com.larkentech.immc2_admin.ModalClasses;


import android.os.Parcel;
import android.os.Parcelable;

public class BookModal {
    private String BookName;
    private String BookPrice160Pages;
    private String BookPrice200Pages;
    private String BookPrice240Pages;
    private String BookDesc;
    private String BookImage;
    private String BookDesigner;
    private String BookCategory;
    private String BookID;
    private String BookSubCategory;

    private String Image1;
    private String Image2;
    private String Image3;
    private String Image4;
    private String Image5;
    private String Image6;
    private String Image7;


    public BookModal() {
    }

    public BookModal(String bookName, String bookPrice160Pages, String bookPrice200Pages, String bookPrice240Pages, String bookDesc, String bookImage, String bookDesigner, String bookCategory, String bookID, String bookSubCategory) {
        BookName = bookName;
        BookPrice160Pages = bookPrice160Pages;
        BookPrice200Pages = bookPrice200Pages;
        BookPrice240Pages = bookPrice240Pages;
        BookDesc = bookDesc;
        BookImage = bookImage;
        BookDesigner = bookDesigner;
        BookCategory = bookCategory;
        BookID = bookID;
        BookSubCategory = bookSubCategory;
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

    public String getImage1() {
        return Image1;
    }

    public void setImage1(String image1) {
        Image1 = image1;
    }

    public String getImage2() {
        return Image2;
    }

    public void setImage2(String image2) {
        Image2 = image2;
    }

    public String getImage3() {
        return Image3;
    }

    public void setImage3(String image3) {
        Image3 = image3;
    }

    public String getImage4() {
        return Image4;
    }

    public void setImage4(String image4) {
        Image4 = image4;
    }

    public String getImage5() {
        return Image5;
    }

    public void setImage5(String image5) {
        Image5 = image5;
    }

    public String getImage6() {
        return Image6;
    }

    public void setImage6(String image6) {
        Image6 = image6;
    }

    public String getImage7() {
        return Image7;
    }

    public void setImage7(String image7) {
        Image7 = image7;
    }
}
