package com.larkentech.immc2_admin;

public class OrderModal {

    private String Address;
    private String BookId;
    private String BookName;
    private String FinalPrice;
    private String Name;
    private String OrderDate;
    private String ItemsCount;
    private String PhoneNumber;
    private String TxnID;

    public OrderModal() {
    }

    public OrderModal(String address, String bookId, String bookName, String finalPrice, String name, String orderDate, String itemsCount, String phoneNumber, String txnID) {
        Address = address;
        BookId = bookId;
        BookName = bookName;
        FinalPrice = finalPrice;
        Name = name;
        OrderDate = orderDate;
       ItemsCount = itemsCount;
        PhoneNumber = phoneNumber;
        TxnID = txnID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
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