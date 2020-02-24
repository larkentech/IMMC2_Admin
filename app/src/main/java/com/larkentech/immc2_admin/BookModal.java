package com.larkentech.immc2_admin;

public class BookModal {
    private String BookName;
    private String BookPrice;
    private String BookDesc;
    private String BookImage;
    private String BookDesigner;
    private String BookCategory;
    private String BookId;
    private String BookSubCategory;

    public BookModal() {
    }

    public BookModal(String bookName, String bookPrice, String bookDesc, String bookImage, String bookDesigner, String bookCategory, String bookId, String bookSubCategory) {
        BookName = bookName;
        BookPrice = bookPrice;
        BookDesc = bookDesc;
        BookImage = bookImage;
        BookDesigner = bookDesigner;
        BookCategory = bookCategory;
        BookId = bookId;
        BookSubCategory = bookSubCategory;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookPrice() {
        return BookPrice;
    }

    public void setBookPrice(String bookPrice) {
        BookPrice = bookPrice;
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

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getBookSubCategory() {
        return BookSubCategory;
    }

    public void setBookSubCategory(String bookSubCategory) {
        BookSubCategory = bookSubCategory;
    }
}
