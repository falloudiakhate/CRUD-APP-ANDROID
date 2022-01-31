package ept.dic2.crud_person_app.activities;

import static android.view.View.GONE;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ept.dic2.crud_person_app.R;
import ept.dic2.crud_person_app.models.Person;
import ept.dic2.crud_person_app.requests.CallAPI;
import ept.dic2.crud_person_app.requests.Endpoints;
import ept.dic2.crud_person_app.tasks.CrudPerson;

public class MainActivity extends AppCompatActivity {


    public static final int CODE_GET_REQUEST = 1024;
    public static final int CODE_PUT_REQUEST = 1025;


    //defining views
    EditText  editTextName, editTextRealname, editTextEmail, editTextDate;
    ImageView logo;
    ProgressBar progressBar;
    ListView listView;
    Button buttonAddUpdate,titre;


    //we will use this list to display person in listview
    List<Person> personList;

    //as the same button is used for create and update
    //we need to track whether it is an update or create operation
    //for this we have this boolean
    boolean isUpdating = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextRealname = (EditText) findViewById(R.id.editTextRealname);
        editTextDate = findViewById(R.id.editTextDate);
        logo = findViewById(R.id.logo);
        titre = (Button) findViewById(R.id.titreLogo);


        buttonAddUpdate = (Button) findViewById(R.id.buttonAddUpdate);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listView = (ListView) findViewById(R.id.listViewPersons);




        buttonAddUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if it is updating
                if (isUpdating) {
                    //calling the method update person
                    updatePerson();
                } else {
                    //if it is not updating
                    //that means it is creating
                    //so calling the method create hero
                    addPerson();
                }
            }
        });

    }

    private void addPerson() {

        String name = editTextName.getText().toString().trim();
        String realname = editTextRealname.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String dateNaissance = editTextDate.getText().toString().trim();


        //validating the inputs
        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Please enter name");
            editTextName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(realname)) {
            editTextRealname.setError("Please enter Last name");
            editTextRealname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter Email");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(dateNaissance)) {
            editTextDate.setError("Please enter Birthday");
            editTextDate.requestFocus();
            return;
        }

        //if validation passes

        HashMap<String, String> params = new HashMap<>();
        params.put("nom", name);
        params.put("prenom", realname);
        params.put("email", email);
        params.put("clef", Endpoints.KEY);
        params.put("dateEnregistrement", "2022-01-30T15:24:05.531Z[UTC]");
        params.put("dateModification", "2022-01-30T15:24:05.531Z[UTC]");
        params.put("dateNaissance", "2022-01-30T15:24:05.531Z[UTC]");


        Log.e("DATA", params.toString());

        //Calling the create person API
        CrudPerson crudPerson = new CrudPerson(this, Endpoints.URL_CREATE_PERSON, params, CODE_PUT_REQUEST);
        crudPerson.execute();
        if(buttonAddUpdate.getText().toString().equalsIgnoreCase("UPDATE")){
            buttonAddUpdate.setText("ADD");
        }

    }

    //calling the method read person to read existing person from the database
    private void readPerson(String key) {
        CrudPerson crudPerson = new CrudPerson(this, Endpoints.URL_READ_PERSON, null, CODE_GET_REQUEST);
        crudPerson.execute();
    }

    private void updatePerson() {

        String name = editTextName.getText().toString().trim();
        String realname = editTextRealname.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String dateNaissance = editTextDate.getText().toString().trim();


        //validating the inputs
        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Please enter name");
            editTextName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(realname)) {
            editTextRealname.setError("Please enter Last name");
            editTextRealname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter Email");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(dateNaissance)) {
            editTextDate.setError("Please enter Birthday");
            editTextDate.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("nom", name);
        params.put("prenom", realname);
        params.put("email", email);
        params.put("clef", Endpoints.KEY);
        params.put("dateEnregistrement", "2022-01-30T15:24:05.531Z[UTC]");
        params.put("dateModification", "2022-01-30T15:24:05.531Z[UTC]");
        params.put("dateNaissance", dateNaissance);

        CrudPerson crudPerson = new CrudPerson(this, Endpoints.URL_UPDATE_PERSON("fff", "ffff"), params, CODE_PUT_REQUEST);
        crudPerson.execute();

        buttonAddUpdate.setText("Add");

        addPerson();

        isUpdating = false;
    }

    private void deletePerson(int id) {
        CrudPerson crudPerson = new CrudPerson(this, Endpoints.URL_DELETE_PERSON("fff", "ttttt"), null, CODE_PUT_REQUEST);
        crudPerson.execute();
    }



    class PersonAdapter extends ArrayAdapter<Person> {

        //our person list
        List<Person> personList;


        //constructor to get the list
        public PersonAdapter(List<Person> personList) {
            super(MainActivity.this, R.layout.layout_person_list, personList);
            this.personList = personList;
        }


        //method returning list item
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.layout_person_list, null, true);

            //getting the textview for displaying name
            TextView textViewName = listViewItem.findViewById(R.id.textViewName);
            TextView textViewEmail = listViewItem.findViewById(R.id.textViewEmail);
            TextView textViewPrenom = listViewItem.findViewById(R.id.textViewPrenom);
            TextView textViewDate = listViewItem.findViewById(R.id.textViewDate);

            //the update and delete textview
            TextView textViewUpdate = listViewItem.findViewById(R.id.textViewUpdate);
            TextView textViewDelete = listViewItem.findViewById(R.id.textViewDelete);

            final Person person = personList.get(position);

            textViewName.setText(person.getNom());
            textViewEmail.setText(person.getPrenom());
            textViewPrenom.setText(person.getEmail());
            textViewDate.setText(person.getDateNaissance());


            ;


            //attaching click listener to update
            textViewUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //so when it is updating we will
                    //make the isUpdating as true
                    isUpdating = true;

                    //we will set the selected hero to the UI elements

                    editTextName.setText(person.getNom());
                    editTextRealname.setText(person.getPrenom());
                    editTextEmail.setText((person.getEmail()));
                    editTextDate.setText(person.getDateNaissance());


                    //we will also make the button text to Update
                    buttonAddUpdate.setText("Update");
                }
            });

            //when the user selected delete
            textViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // we will display a confirmation dialog before deleting
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("Delete " + person.getNom())
                            .setMessage("Are you sure you want to delete it?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //if the choice is yes we will delete the hero

                                    deletePerson(1);
                                    listView.setVisibility(View.INVISIBLE);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
            });

            return listViewItem;
        }
    }


    //inner class to perform network request extending an AsyncTask
    public class CrudPerson extends AsyncTask<Void, Void, String> {


        ProgressBar progressBar;


        //the url where we need to send the request
        String url;

        //the parameters
        HashMap<String, String> params;

        //the request code to definrequestURLe whether it is a GET or POST
        int requestCode;

        private final MainActivity context;

        //constructor to initialize values
        public  CrudPerson(MainActivity context, String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
            this.context = context;
        }



        //when the task started displaying a progressbar
        @Override
        protected void onPreExecute() {
//        progressBar = (ProgressBar) MainActivity.findViewById(R.id.progressBar);
            super.onPreExecute();
//        progressBar.setVisibility(View.VISIBLE);
        }


        //this method will give the response from the request
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


        progressBar.setVisibility(GONE);
        try {
            JSONObject object = new JSONObject(s);

            if (!object.getBoolean("error")) {
                Toast.makeText(context, object.getString("message"), Toast.LENGTH_SHORT).show();
                //refreshing the herolist after every operation
                //so we get an updated list
//                MainActivity.refreshPersonList(object.getJSONArray("persons"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

            editTextName.setText("");
            editTextRealname.setText("");
            editTextEmail.setText("");
            editTextDate.setText("");

        }

        //the network operation will be performed in background
        @Override
        protected String doInBackground(Void... voids) {
            CallAPI callAPI = new CallAPI();

            if (requestCode == CODE_PUT_REQUEST)
                return callAPI.sendPutRequest(url, params);


            if (requestCode == CODE_GET_REQUEST)
                return callAPI.sendGetRequest(url);

            return null;
        }
    }


}