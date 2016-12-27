package com.yftech.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yftech.greendao.db.DaoMaster;
import com.yftech.greendao.db.DaoSession;
import com.yftech.greendao.db.UserDao;


public class MainActivity extends AppCompatActivity {


    UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //还是要根据一个helper拿到db
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"demo.db",null);
        SQLiteDatabase database = helper.getWritableDatabase();
        //通过master开启会话,拿到dao(数据访问接口)
        DaoMaster master=new DaoMaster(database);
        DaoSession session = master.newSession();
        userDao = session.getUserDao();

        User user = new User((long) 1,"colin","admin");

        userDao.insert(user);


        //查询
//        QueryBuilder queryBuilder = userDao.queryBuilder();
//        queryBuilder.where(UserDao.Properties.Name.eq("colin"))//查询条件
//                .orderAsc(UserDao.Properties.Id)//设置排序
//                .list();//返回结果

    }
}
