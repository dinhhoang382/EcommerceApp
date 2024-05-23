package com.dinhthehoang.ecommerceapp.Helper;

import android.content.Context;
import android.widget.Toast;

import com.dinhthehoang.ecommerceapp.Domain.PopularDomain;

import java.util.ArrayList;

public class ManagmentCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagmentCart(Context context) {
        this.context = context;
        tinyDB = new TinyDB(context);
    }
    public void insertFood(PopularDomain item){
        ArrayList<PopularDomain> listPop = getListCart();
        boolean exitAlredy = false;
        int n = 0;
        for(int i = 0; i < listPop.size(); i++){
            if(listPop.get(i).getTitle().equals(item.getTitle())){
                exitAlredy = true;
                n = i;
                break;
            }
        }
        if(exitAlredy){
            listPop.get(n).setNumberinCart(item.getNumberinCart());
        }
        else {
            listPop.add(item);
        }
        tinyDB.putListObject("CartList", listPop);
        Toast.makeText(context, "Added to cart successfully!", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<PopularDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }
    public void minusNumberItem(ArrayList<PopularDomain> listItem, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if(listItem.get(position).getNumberinCart() == 1){
            listItem.remove(position);
        }
        else{
            listItem.get(position).setNumberinCart(listItem.get(position).getNumberinCart() - 1);
        }
        tinyDB.putListObject("CartList", listItem);
        changeNumberItemsListener.change();
    }
    public void plusNumberItem(ArrayList<PopularDomain> listItem, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listItem.get(position).setNumberinCart(listItem.get(position).getNumberinCart() + 1);
        tinyDB.putListObject("CartList", listItem);
        changeNumberItemsListener.change();
    }
    public Double getTotalFee(){
        ArrayList<PopularDomain> listItem = getListCart();
        double fee = 0;
        for(int i = 0; i < listItem.size(); i++){
            fee += listItem.get(i).getPrice() * listItem.get(i).getNumberinCart();
        }
        return fee;

    }
}
