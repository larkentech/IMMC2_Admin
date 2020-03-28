package com.larkentech.immc2_admin;

public class OrderModal {

    private String Address;
    private String BookID;
    private String BookName;
    private String FinalPrice;
    private String Name;
    private String OrderDate;
    private String ItemsCount;
    private String PhoneNumber;
    private String TxnID;
    private String Pages;

    public String getPages() {
        return Pages;
    }

    public void setPages(String pages) {
        Pages = pages;
    }

    public OrderModal() {

    }

    public OrderModal(String address, String bookId, String bookName, String finalPrice, String name, String orderDate, String itemsCount, String phoneNumber, String txnID, String pages) {
        Address = address;
        BookID = bookId;
        BookName = bookName;
        FinalPrice = finalPrice;
        Name = name;
        OrderDate = orderDate;
       ItemsCount = itemsCount;
        PhoneNumber = phoneNumber;
        TxnID = txnID;
        this.Pages = pages;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String bookId) {
        BookID = bookId;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getFinalPrice() {
        return FinalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        FinalPrice = finalPrice;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }


    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getTxnID() {
        return TxnID;
    }

    public void setTxnID(String txnID) {
        TxnID = txnID;
    }

    public String getItemsCount() {
        return ItemsCount;
    }

    public void setItemsCount(String itemsCount) {
        ItemsCount = itemsCount;
    }
}