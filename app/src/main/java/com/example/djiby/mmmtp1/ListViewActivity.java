package com.example.djiby.mmmtp1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by djiby on 18/01/17.
 */

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;
    private SimpleAdapter adapter;
    //private ArrayList<HashMap<String, String>> listItem;


    // private CustomerDbHelper customerDbHelper;
    //  private SQLiteDatabase sqLiteDatabase;

    //Get Customer info when clicked on listview item
    TextView name;
    TextView firstname;
    TextView date;
    TextView city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview);

        //Database initialisations
        //customerDbHelper = new CustomerDbHelper(this);
        // sqLiteDatabase = customerDbHelper.getWritableDatabase();


        listView = (ListView) findViewById(R.id.listviewinfo);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                name = (TextView) view.findViewById(R.id.viewName);
                firstname = (TextView) view.findViewById(R.id.viewFirstName);
                date = (TextView) view.findViewById(R.id.viewDate);
                city = (TextView) view.findViewById(R.id.viewCity);

                showCustomerInfo();
            }
        });
        /*listItem = new ArrayList<HashMap<String, String>>();

        //Exercise 2.1
       HashMap<String, String> map = new HashMap<>();

        Customer c1 = new Customer("name1", "firstName1", "19/10/2000", "Dakar");
        c1.mapping(map, listItem);

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_NOM, "name1");
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, "firstName1");
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, "19/10/2000");
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, "Dakar");
        //sqLiteDatabase.insert(CustomerContract.FeedEntry.TABLE_NAME, null, values);

        Customer c2 = new Customer("name2", "firstName2", "19/10/2000", "Dakar");
        c2.mapping(map, listItem);

        // Create a new map of values, where column names are the keys
        ContentValues values2 = new ContentValues();
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_NOM, "name2");
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, "firstName2");
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, "19/10/2000");
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, "Dakar");
        //sqLiteDatabase.insert(CustomerContract.FeedEntry.TABLE_NAME, null, values2);

        Customer c3 = new Customer("name3", "firstName3", "19/10/2000", "Dakar");
        c3.mapping(map, listItem);

        // Create a new map of values, where column names are the keys
        ContentValues values3 = new ContentValues();
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_NOM, "name3");
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, "firstName3");
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, "19/10/2000");
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, "Dakar");
        //sqLiteDatabase.insert(CustomerContract.FeedEntry.TABLE_NAME, null, values3);

        Customer c4 = new Customer("name4", "firstName4", "19/10/2000", "Dakar");
        c4.mapping(map, listItem);

        // Create a new map of values, where column names are the keys
        ContentValues values4 = new ContentValues();
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_NOM, "name4");
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, "firstName4");
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, "19/10/2000");
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, "Dakar");
       // sqLiteDatabase.insert(CustomerContract.FeedEntry.TABLE_NAME, null, values4);

        Customer c5 = new Customer("name5", "firstName5", "19/10/2000", "Dakar");
        c5.mapping(map, listItem);

        // Create a new map of values, where column names are the keys
        ContentValues values5 = new ContentValues();
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_NOM, "name5");
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, "firstName5");
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, "19/10/2000");
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, "Dakar");
        //sqLiteDatabase.insert(CustomerContract.FeedEntry.TABLE_NAME, null, values5);

        //getting information from the intent of the mai nactivity
        Customer newCustomer = getIntent().getParcelableExtra("customer");
        newCustomer.mapping(map, listItem);

        // Create a new map of values, where column names are the keys
        ContentValues valuesNew = new ContentValues();
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_NOM, newCustomer.getName());
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, newCustomer.getFirstName());
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, newCustomer.getDayOfBirth());
        values.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, newCustomer.getCity());
        //sqLiteDatabase.insert(CustomerContract.FeedEntry.TABLE_NAME, null, valuesNew);
        */

       /* adapter = new SimpleAdapter(this.getBaseContext(),
                listItem, R.layout.item,
                new String[]{"Name", "First Name", "Date", "City"},
                new int[]{R.id.viewName, R.id.viewFirstName, R.id.viewDate, R.id.viewCity});
        listView.setAdapter(adapter);*/

        //Exercise 2.2
        Cursor cursor = getContentResolver().query(CustomerProvider.CONTENT_URI, null, null, null, null);

        CursorAdapter mCursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.item,
                cursor,
                new String[]{CustomerContract.FeedEntry.COLUMN_NAME_NOM, CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, CustomerContract.FeedEntry.COLUMN_NAME_DATE, CustomerContract.FeedEntry.COLUMN_NAME_VILLE},
                new int[]{R.id.viewName, R.id.viewFirstName, R.id.viewDate, R.id.viewCity},
                0);

        listView.setAdapter(mCursorAdapter);

    }

    private void showCustomerInfo() {
        String customerInfo = name.getText().toString() +
                "\n" + firstname.getText().toString() +
                "\n" + date.getText().toString() +
                "\n" + city.getText().toString();
        Intent intent = new Intent(this, ShowCustomerInFoActivity.class);
        intent.putExtra("information", customerInfo);
        startActivity(intent);
    }

    public void createNewCustomer(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void findCustomer(View view) {
        EditText customerName = (EditText) findViewById(R.id.editFilterCustomer);

        Cursor cursor;
        if (customerName.getText().toString().length() == 0) {
            cursor = getContentResolver().query(CustomerProvider.CONTENT_URI, null, null, null, null);
        } else {
            cursor = getContentResolver().query(CustomerProvider.CONTENT_URI,
                    null,
                    "nom = ? ", new String[]{customerName.getText().toString()},
                    null);
        }

        CursorAdapter mCursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.item,
                cursor,
                new String[]{CustomerContract.FeedEntry.COLUMN_NAME_NOM, CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, CustomerContract.FeedEntry.COLUMN_NAME_DATE, CustomerContract.FeedEntry.COLUMN_NAME_VILLE},
                new int[]{R.id.viewName, R.id.viewFirstName, R.id.viewDate, R.id.viewCity},
                0);

        listView.setAdapter(mCursorAdapter);


    }
}
