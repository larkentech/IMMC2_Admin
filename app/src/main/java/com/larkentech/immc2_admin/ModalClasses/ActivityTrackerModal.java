package com.larkentech.immc2_admin.ModalClasses;

public class ActivityTrackerModal {

    private String BookID;
    private String BookName;
    private String BookImage;
    private String BookCategory;
    private String BookSubCategory;
    private Boolean isChecked=false;

    public ActivityTrackerModal() {
    }

    public ActivityTrackerModal(String bookID, String bookName, String bookImage, String bookCategory, String bookSubCategory, Boolean isChecked) {
        BookID = bookID;
        BookName = bookName;
        BookImage = bookImage;
        BookCategory = bookCategory;
        BookSubCategory = bookSubCategory;
        this.isChecked = isChecked;
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

    public String getBookCategory() {
        return BookCategory;
    }

    public void setBookCategory(String bookCategory) {
        BookCategory = bookCategory;
    }

    public String getBookSubCategory() {
        return BookSubCategory;
    }

    public void setBookSubCategory(String bookSubCategory) {
        BookSubCategory = bookSubCategory;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
