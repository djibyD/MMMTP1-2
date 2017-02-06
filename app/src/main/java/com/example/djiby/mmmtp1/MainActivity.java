package com.example.djiby.mmmtp1;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText nom;
    EditText prenom;
    EditText date;
    EditText ville;

    //new field added for the phone number
    EditText phoneNumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom = (EditText) findViewById(R.id.editName);
        prenom = (EditText) findViewById(R.id.editFirstName);
        date = (EditText) findViewById(R.id.editDate);
        ville = (EditText) findViewById(R.id.editCity);
    }

    public void valider(View view) {
        /*String informations = nom.getText() + "\n" + prenom.getText() + "\n" + date.getText() + "\n" + ville.getText() + "\n";
        Toast.makeText(getApplicationContext(), informations.toString(),
                Toast.LENGTH_SHORT).show();
        intent.putExtra("information", informations);
        Intent intent = new Intent(this, ShowCustomerInFoActivity.class);*/

        //TP2-Exercice2-ContentProvider
        if (nom.getText().length() == 0 && prenom.getText().length() == 0 && date.getText().length() == 0 && ville.getText().length() == 0) {
            Intent intent = new Intent(this, ListViewActivity.class);
            //intent.putExtra("customer", customer);
            startActivity(intent);
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CustomerContract.FeedEntry.COLUMN_NAME_NOM, nom.getText().toString());
            contentValues.put(CustomerContract.FeedEntry.COLUMN_NAME_PRENOM, prenom.getText().toString());
            contentValues.put(CustomerContract.FeedEntry.COLUMN_NAME_DATE, date.getText().toString());
            contentValues.put(CustomerContract.FeedEntry.COLUMN_NAME_VILLE, ville.getText().toString());

            Uri uri = getContentResolver().insert(CustomerProvider.CONTENT_URI, contentValues);

            //Customer customer = new Customer(nom.getText().toString(), prenom.getText().toString(), date.getText().toString(), ville.getText().toString());
            Intent intent = new Intent(this, ListViewActivity.class);
            //intent.putExtra("customer", customer);
            startActivity(intent);
        }

        // onDestroy();
        //SetResult and finish
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.itemReset:
                resetData();
                return true;
            case R.id.itemNew:
                newField();
                return true;
            case R.id.itemBrowseCity:
                browseCity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void browseCity() {
        EditText editText = (EditText) findViewById(R.id.editCity);
        String path = "http://fr.wikipedia.org" + "/wiki/" + editText.getText();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
        startActivity(intent);
    }

    private void newField() {
        phoneNumer = new EditText(getApplicationContext());
        phoneNumer.setHint("Phone number");
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.zoneTextS);
        linearLayout.addView(phoneNumer);

    }

    private void resetData() {
        EditText nom = (EditText) findViewById(R.id.editName);
        nom.setText("");
        EditText prenom = (EditText) findViewById(R.id.editFirstName);
        prenom.setText("");
        EditText date = (EditText) findViewById(R.id.editDate);
        date.setText("");
        EditText ville = (EditText) findViewById(R.id.editCity);
        ville.setText("");

        //remove the new field is case it's been added
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.zoneTextS);
        linearLayout.removeView(phoneNumer);
    }
}
