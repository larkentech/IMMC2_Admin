package com.larkentech.immc2_admin.ModalClasses;

public class ActivityTrackerModal {

    private String BookID;
    private String BookName;
    private String BookImage;
    private String Category;
    private String SubCategory;

    public ActivityTrackerModal() {
    }

    public ActivityTrackerModal(String bookID, String bookName, String bookImage, String category, String subCategory) {
        BookID = bookID;
        BookName = bookName;
        BookImage = bookImage;
        Category = category;
        SubCategory = subCategory;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String bookID) {
        BookID = bookID;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookImage() {
        return BookImage;
    }

    public void setBookImage(String bookImage) {
        BookImage = bookImage;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSubCategory() {
        return SubCategory;
    }

    public void setSubCategory(String subCategory) {
        SubCategory = subCategory;
    }
}
