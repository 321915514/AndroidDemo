package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.database.AppDatabase;


import com.example.myapplication.database.entity.Address;
import com.example.myapplication.database.entity.Consumer;
import com.example.myapplication.database.entity.User;
import com.example.myapplication.util.UtToast;


import java.util.List;

public class RoomDatabaseActivity extends AppCompatActivity {

    private AppDatabase mAppDatabase;

    private Button mAddUser, mSelectUser, mUpdate, mDelete,mSelectAddress,mAddConsumer,mFindConsumer,mAddAddress,mSelectAllConsumer;

    private EditText mName,mAge,mDescription,mInput,mInputAddress,mSelectConsumer,mUsername,mPassword,mCity,mUpdatename,mUpdateAge,mUpdateCity,mUpdateID;

    private TextView mShowData;

    private String name,description;

    private Integer age = -1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_database);
        mAddUser = findViewById(R.id.add_user);

        mSelectUser = findViewById(R.id.select_user);
        mShowData = findViewById(R.id.show_data);
        mUpdate = findViewById(R.id.update_user);
        mDelete = findViewById(R.id.delete);
        mInput = findViewById(R.id.Edit_text);
        mName = findViewById(R.id.name);
        mAge = findViewById(R.id.age);
        mDescription = findViewById(R.id.description);
        mSelectAddress = findViewById(R.id.select_address);

        mAddConsumer = findViewById(R.id.add_consumer);
        mFindConsumer = findViewById(R.id.select_consumer_by_city);

        mAddAddress = findViewById(R.id.add_address);

        mInputAddress = findViewById(R.id.add_address_edit);

        mSelectConsumer = findViewById(R.id.select_consumer_edit);
        mSelectAllConsumer = findViewById(R.id.select_all_consumer);

        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mCity = findViewById(R.id.city);

        mUpdatename = findViewById(R.id.name_update);

        mUpdateAge = findViewById(R.id.age_update);

        mUpdateCity = findViewById(R.id.city_update);
        mUpdateID = findViewById(R.id.id_update);


        OnClick onClick = new OnClick();

        mAppDatabase =AppDatabase.getInstance(getApplicationContext());

        mAddUser.setOnClickListener(onClick);
        mSelectUser.setOnClickListener(onClick);
        mUpdate.setOnClickListener(onClick);
        mDelete.setOnClickListener(onClick);
        mInput.setOnClickListener(onClick);
        mSelectAddress.setOnClickListener(onClick);
        mFindConsumer.setOnClickListener(onClick);
        mAddConsumer.setOnClickListener(onClick);
        mAddAddress.setOnClickListener(onClick);

        mSelectAllConsumer.setOnClickListener(onClick);




    }


    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private <T> String  show(List<T> list){
        String s ="";

        for (T item: list) {

            String name = new StringBuilder(item.getClass().getName()).reverse().toString();

            String strings = new StringBuilder(name.split("\\.")[0]).reverse().toString();
            //UtToast.toast(getApplicationContext(), s.toString(),Toast.LENGTH_SHORT);
            switch (strings){
                case "Consumer":
                    Consumer consumer = (Consumer)item;
                    s += " "+String.format("%s%s%s", consumer.getUsername(),consumer.getPassword(),findAddressByID(consumer.getAddress_id()).getCity())+", ";
                    break;
                case "User":
                    User user = (User) item;
                    s += " "+String.format("%d%s%d%s%s%s%s%s", user.getU_id(), user.getName(),
                            user.getAge()," "+user.getHeight(),user.getDescription(), user.getAddress().getCountry(),user.getAddress().getProvince(),user.getAddress().getCity())+", ";
                    break;
                case "Address":
                    Address address = (Address) item;
                    s += " "+String.format("%d%s%s%s", address.getId(), address.getCountry(), address.getProvince(), address.getCity())+",";

                    break;

            }


        }
        return s;
    }





    public List<User> findUsers() {
        return mAppDatabase.userDao().getUsers();
    }


    public Long addUser(User user) {
        return mAppDatabase.userDao().addUser(user);

    }
    public Long insertConsumer(Consumer consumer){
        return mAppDatabase.ConsumerDao().insertConsumer(consumer);
    }
    public User findUserByID(int id){
        return mAppDatabase.userDao().findUserById(id);
    }

    public void updateUser(User user){
        mAppDatabase.userDao().updateUser(user);

    }

    public int  deleteUser(User user) {
       return mAppDatabase.userDao().deleteUserById(user);
    }


    public List<Address> findAddress(){
        return mAppDatabase.AddressDao().selectAddress();
    }

    public Address findAddressByID(int id){
        return mAppDatabase.AddressDao().selectAddressByID(id);
    }




    public class OnClick implements View.OnClickListener {

        @SuppressLint({"SetTextI18n", "DefaultLocale"})
        @Override
        public void onClick(View view) {
            Long result =0l;
            switch (view.getId()) {
                case R.id.add_user:
                    if(!mName.getText().toString().equals("")&&!mAge.getText().toString().equals("") &&!mDescription.getText().toString().equals("")) {
                        name = mName.getText().toString();
                        age = Integer.parseInt(mAge.getText().toString());
                        description = mDescription.getText().toString();
                        Log.e("TAG", "add_user" + addUser(new User(name,age,description,80,new Address("中国","广东","深圳"))));
                        mName.getText().clear();
                        mAge.getText().clear();
                        mDescription.getText().clear();
                    }
                    break;
                case R.id.select_user:
                    if(findUsers().size()>0) {
                        mShowData.setText("user[" + show(findUsers()) + "]");
                    }
                    break;
                case R.id.update_user:
                    if(mUpdateID.getText().toString().equals("")||mUpdateID.getText() == null) return;
                    User user = findUserByID(Integer.valueOf(mUpdateID.getText().toString()));
                    if (user != null){
                        if(mUpdatename.getText().toString().equals("")||mUpdateAge.getText().toString().equals("")||mUpdateCity.getText().toString().equals("")){
                            UtToast.toast(getApplicationContext(),"姓名或年龄或城市不能为空",Toast.LENGTH_SHORT);
                            return;
                        }
                        user.setU_id(user.getU_id());
                        user.setName(mUpdatename.getText().toString());
                        user.setAge(Integer.valueOf(mUpdateAge.getText().toString()));
                        user.setAddress(user.getAddress().getCountry(),user.getAddress().getProvince(),mUpdateCity.getText().toString());
                        updateUser(user);
                    }else {
                        UtToast.toast(getApplicationContext(),"无此用户",Toast.LENGTH_SHORT);
                    }
                    mUpdateID.getText().clear();
                    mUpdatename.getText().clear();
                    mUpdateAge.getText().clear();
                    mUpdateCity.getText().clear();
                    break;
                case R.id.delete:
                    if(mInput.getText().toString().equals("")) break;
                    User user1 = findUserByID(Integer.valueOf(mInput.getText().toString()));
                    if(user1 != null){
                        Log.e("TAG",""+user1.getU_id());
                        UtToast.toast(getApplicationContext(),"index is "+deleteUser(user1),Toast.LENGTH_SHORT);
                        mInput.getText().clear();
                        break;
                    }else {
                        Toast.makeText(getApplicationContext(),"没有用户",Toast.LENGTH_LONG).show();
                        mInput.getText().clear();
                        break;
                    }
                case R.id.select_address:
                    if(findAddress().size()>0){
                        mShowData.setText("address["+show(findAddress())+" ]");
                    }
                    break;
                case R.id.add_consumer:
                    if(!mUsername.getText().toString().equals("")&&!mPassword.getText().toString().equals("")&&!mCity.getText().toString().equals("")){
                        int address_id = mAppDatabase.AddressDao().findAddressIDByCity(mCity.getText().toString());

                        Long index = insertConsumer(new Consumer(mUsername.getText().toString(),mPassword.getText().toString(),address_id));

                        UtToast.toast(getApplicationContext(),"index is"+index,Toast.LENGTH_SHORT);
                    }
                    mUsername.getText().clear();
                    mPassword.getText().clear();
                    mCity.getText().clear();
                    break;
                case R.id.select_consumer_by_city:
                    List<Consumer> list = mAppDatabase.ConsumerDao().findConsumerByCity(mSelectConsumer.getText().toString());
                    if(list!= null){
                        Toast.makeText(RoomDatabaseActivity.this,""+list.size(),Toast.LENGTH_LONG).show();
                        mShowData.setText("consumer[ "+show(list)+"]");
                    }
                    mSelectConsumer.getText().clear();
                    break;
                case R.id.add_address:
                    if(mInputAddress.getText().toString().equals("")) return;
                    String addressInput = mInputAddress.getText().toString();
                    Address address = new Address();
                    address.setCountry("中国");
                    address.setProvince("广州");
                    address.setCity(addressInput);
                    result = mAppDatabase.AddressDao().insertAll(address);
                    if(result>0){
                        UtToast.toast(getApplicationContext(),"index is"+result,Toast.LENGTH_SHORT);
                        mInputAddress.getText().clear();
                    }
                    break;
                case R.id.select_all_consumer:
                    List<Consumer> consumers = mAppDatabase.ConsumerDao().findConsumers();
                    mShowData.setText("consumer[ "+show(consumers)+" ]");

                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }

    }

}


