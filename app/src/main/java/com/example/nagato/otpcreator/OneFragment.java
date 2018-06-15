package com.example.nagato.otpcreator;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nagato.otpcreator.recycler.ContactsAdapter;
import com.example.nagato.otpcreator.recycler.ContactsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OneFragment extends Fragment {

    RecyclerView rView;
    LinearLayoutManager linearLayoutManager;

    public static OneFragment newInstance() {
        return new OneFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);

        // RecyclerView Creation
        rView = rootView.findViewById(R.id.contacts_builder);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rView.setLayoutManager(linearLayoutManager);
        ArrayList<ContactsModel> empty = new ArrayList<>();
        RecyclerView.Adapter emptyAdapter = new ContactsAdapter(empty, getContext());
        rView.setAdapter(emptyAdapter);

        ///////////////////////////////////////////////////////////////////////////////////////////
        //Create Static JSON Data
        JSONObject contact1 = new JSONObject();
        try {
            contact1.put("id", "1");
            contact1.put("first_name", "Test");
            contact1.put("last_name", "Number");
            contact1.put("phone", "919198003726");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject contact2 = new JSONObject();
        try {
            contact2.put("id", "2");
            contact2.put("first_name", "Test");
            contact2.put("last_name", "Number2");
            contact2.put("phone", "917745943774");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject contact3 = new JSONObject();
        try {
            contact3.put("id", "3");
            contact3.put("first_name", "Test");
            contact3.put("last_name", "Number3");
            contact3.put("phone", "919971792703");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Add to single JSON Object
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(contact1);
        jsonArray.put(contact2);
        jsonArray.put(contact3);

        JSONObject contactsObj = new JSONObject();
        try {
            contactsObj.put("Students", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////////////////////////////////////////////////

        //Start filling recylerView
        ArrayList<ContactsModel> data = new ArrayList<>();

        JSONObject jsonObject;
        for(int i=0; i<jsonArray.length(); i++)
        {
            try {
                jsonObject = jsonArray.getJSONObject(i);
                String first = jsonObject.getString("first_name");
                String last = jsonObject.getString("last_name");
                String phone = jsonObject.getString("phone");

                data.add(new ContactsModel(
                        first,
                        last,
                        phone
                ));

                RecyclerView.Adapter dataAdapter = new ContactsAdapter(data, getContext());
                rView.setAdapter(dataAdapter);
                rView.setItemAnimator(new DefaultItemAnimator());
                rView.setHasFixedSize(true);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return rootView;
    }








    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
