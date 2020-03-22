package com.larkentech.immc2_admin;

public class BookModal {
    private String BookName;
    private String BookPrice160Pages;
    private String BookPrice200Pages;
    private String BookPrice240Pages;
    private String BookDesc;
    private String BookImage;
    private String BookDesigner;
    private String BookCategory;
    private String BookId;
    private String BookSubCategory;

    public BookModal() {
    }

    public BookModal(String bookName, String bookPrice160Pages, String bookPrice200Pages, String bookPrice240Pages, String bookDesc, String bookImage, String bookDesigner, String bookCategory, String bookId, String bookSubCategory) {
        BookName = bookName;
        BookPrice160Pages = bookPrice160Pages;
        BookPrice200Pages = bookPrice200Pages;
        BookPrice240Pages = bookPrice240Pages;
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
