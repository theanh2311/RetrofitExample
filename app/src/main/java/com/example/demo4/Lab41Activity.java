package com.example.demo4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.demo4.delete.InterfaceDelete;
import com.example.demo4.delete.PrdDelete;
import com.example.demo4.delete.ServerResponseDelete;
import com.example.demo4.insert.InterfaceInsertProduct;
import com.example.demo4.insert.Product;
import com.example.demo4.insert.ServerResponseProduct;
import com.example.demo4.select.InterfaceSelectProd;
import com.example.demo4.select.Prod;
import com.example.demo4.select.ServerResponseSelectProd;
import com.example.demo4.update.InterfaceUpdate;
import com.example.demo4.update.PrdUpdate;
import com.example.demo4.update.ServerResponseUpdate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Lab41Activity extends AppCompatActivity {
TextView tvKQ;
    String strKQ = "";
EditText txtId,txtName,txtPrice,txtDes;
Button btnInsert,btnSelect,btnDelete,btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab41);
        txtId = findViewById(R.id.edtId);
        txtName = findViewById(R.id.edtName);
        tvKQ = findViewById(R.id.textView2);
        txtPrice = findViewById(R.id.edtPrice);
        txtDes = findViewById(R.id.edtDes);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnSelect = findViewById(R.id.btnSelect);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData1();
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectData1();
            }
        });

    }
    public void insertData1() {
        //b1 - Tao doi tuong chua du lieu và đưa dữ liệu vào đối tượng
        Product prd = new Product();
        prd.setName(txtName.getText().toString());
        prd.setPrice(txtPrice.getText().toString());
        prd.setDescription(txtDes.getText().toString());
        //b2. tao doi tuong retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://emsiat.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b3: goi ham insert
        InterfaceInsertProduct insertPrdObj = retrofit.create(InterfaceInsertProduct.class);
        //B3.1.chuan bi
        Call<ServerResponseProduct> call
                =insertPrdObj.insertProduct(prd.getName(),prd.getPrice(),prd.getDescription());
        //b3.1. thuc thi ham
        call.enqueue(new Callback<ServerResponseProduct>() {
            //neu thanh cong
            @Override
            public void onResponse(Call<ServerResponseProduct> call, Response<ServerResponseProduct> response) {
                ServerResponseProduct serverResponsePrd = response.body();//lay ve du lieu
                tvKQ.setText("Insert thanh cong");//dua ra thong bao

            }
            //neu that bai
            @Override
            public void onFailure(Call<ServerResponseProduct> call, Throwable t) {
                tvKQ.setText(t.getMessage());//thong bao that bai
            }
        });
    }

        public void selectData1( ){
        strKQ = "";
            //1tao doi tuong retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://emsiat.000webhostapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            //2 goi ham select trong interface
            //2.1 tao doi tuong
            InterfaceSelectProd interfaceSelectProd = retrofit.create(InterfaceSelectProd.class);
            //2.2 chuan bi ham
            Call<ServerResponseSelectProd> call = interfaceSelectProd.getProduct();
            //2.3 thuc thi lenh
            call.enqueue(new Callback<ServerResponseSelectProd>() {
                @Override
                public void onResponse(Call<ServerResponseSelectProd> call, Response<ServerResponseSelectProd> response) {
                    ServerResponseSelectProd serverResponseSelectProd = response.body();//lay ket qua tra ve
                    //chuyen ket qua sang list
                    List<Prod> ls = new ArrayList<>(Arrays.asList(serverResponseSelectProd.getPRODUCT()));
                    //dua vao vong for de doc
                    for(Prod p: ls)
                    {
                        strKQ +="Name: "+p.getName()+"; Price: "+p.getPrice()+
                                "; Des: "+p.getDescription()+"\n\n";
                    }
                    //dua ket qua len text
                    tvKQ.setText(strKQ);
                    Log.d("kq",strKQ);
                }

                @Override
                public void onFailure(Call<ServerResponseSelectProd> call, Throwable t) {
                    tvKQ.setText(t.getMessage());
                }
            });
        }

    public void Delete(View view) {
        //dua du lieu vao doi tuong
        PrdDelete p = new PrdDelete();
        p.setpId(txtId.getText().toString());
        //tao doi tuong retrofit
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://emsiat.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //chuan bi ham
        InterfaceDelete interfaceDelete = retrofit.create(InterfaceDelete.class);
        Call<ServerResponseDelete> call = interfaceDelete.deletePrd(p.getpId());
        //thuc thi ham
        call.enqueue(new Callback<ServerResponseDelete>() {
            @Override
            public void onResponse(Call<ServerResponseDelete> call, Response<ServerResponseDelete> response) {
                ServerResponseDelete svr = response.body();
                tvKQ.setText("Delete thanh cong  -  "+svr.getMessage());
            }

            @Override
            public void onFailure(Call<ServerResponseDelete> call, Throwable t) {
                tvKQ.setText(""+t.getMessage());
            }
        });
    }

    public void Update(View view) {
        //B1 - Dua du lieu vao doi tuong
        PrdUpdate p = new PrdUpdate();
        p.setpId(txtId.getText().toString());
        p.setName(txtName.getText().toString());
        p.setPrice(txtPrice.getText().toString());
        p.setDescription(txtDes.getText().toString());
//B2 - Tao doi tuong retrofilt
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://emsiat.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //B3. Chuan bi ham
        InterfaceUpdate interfaceUpdate = retrofit.create(InterfaceUpdate.class);
        Call<ServerResponseUpdate> call = interfaceUpdate.update(p.getpId(),
                p.getName(),p.getPrice(),p.getDescription());
        //B4. thuc thi ham
        call.enqueue(new Callback<ServerResponseUpdate>() {
            //neu thanh cong
            @Override
            public void onResponse(Call<ServerResponseUpdate> call, Response<ServerResponseUpdate> response) {
                ServerResponseUpdate svr = response.body();
                tvKQ.setText(svr.getMessage());
            }
            //neu that bao
            @Override
            public void onFailure(Call<ServerResponseUpdate> call, Throwable t) {
                tvKQ.setText(t.getMessage());
            }
        });
    }
}