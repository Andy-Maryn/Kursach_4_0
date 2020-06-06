package com.example.kursach_4_0.orm;

import java.util.List;

public interface  IDatabaseHandler {
    public void addMyTown(MyTown myTown);
    public MyTown getMyTown(int id);
    public List<MyTown> getAllMyTown();
    public int getMyTownCount();
    public int updateMyTown(MyTown myTown);
    public void deleteMyTown(MyTown myTown);
    public void deleteAll();
}
