package com.example.nagato.otpcreator;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nagato.otpcreator.recycler.ContactsAdapter;
import com.example.nagato.otpcreator.recycler.ContactsModel;
import com.example.nagato.otpcreator.recycler.MessageAdapter;
import com.example.nagato.otpcreator.recycler.MessageModel;
import com.example.nagato.otpcreator.retrofit.Client;
import com.example.nagato.otpcreator.retrofit.ServiceGenerator;
import com.example.nagato.otpcreator.retrofit.TAB2DataModel;
import com.example.nagato.otpcreator.retrofit.TAB2MsgModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TwoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView rView;
    LinearLayoutManager linearLayoutManager;
    SwipeRefreshLayout swipeLayout;

    Client client;
    Call<TAB2MsgModel> listCall;
    TAB2MsgModel responseData;

    String API_KEY;
    Snackbar snackbar;

    private View rootView;


    public static TwoFragment newInstance() {
        return new TwoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_two, container, false);

        // RecyclerView Creation
        rView = rootView.findViewById(R.id.message_builder);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rView.setLayoutManager(linearLayoutManager);
        ArrayList<ContactsModel> empty = new ArrayList<>();
        RecyclerView.Adapter emptyAdapter = new ContactsAdapter(empty, getContext());
        rView.setAdapter(emptyAdapter);

        //Swipe to refresh
        swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_blue_dark),
                getResources().getColor(android.R.color.holo_orange_dark));

        API_KEY = "/XC4jgFfqig-yi2tN6o9gGYgGMb4GTr4F2IwksgUow"; // custom api key

        client = ServiceGenerator.createService(Client.class); // create retrofit service

        get_msg(rootView, API_KEY);

        return rootView;
    }

    public void get_msg(final View anchorView, String api_key)
    {
        listCall = client.receive(api_key); // retrofit call
        listCall.enqueue(new Callback<TAB2MsgModel>() {
            @Override
            public void onResponse(Call<TAB2MsgModel> call, Response<TAB2MsgModel> response) {

                responseData = response.body(); // get response onto the Response Model

                //Fill RecyclerView
                ArrayList<MessageModel> data = new ArrayList<>();
                ArrayList<TAB2DataModel> tab2Data = responseData.getMessages();
                for(TAB2DataModel item : tab2Data)
                {
                    data.add(new MessageModel(
                            item.getNumber(),
                            item.getDateTime(),
                            item.getContent()
                    ));
                }

                RecyclerView.Adapter dataAdapter = new MessageAdapter(data, getContext());
                rView.setAdapter(dataAdapter);
                rView.setItemAnimator(new DefaultItemAnimator());
                rView.setHasFixedSize(true);

            }

            @Override
            public void onFailure(Call<TAB2MsgModel> call, Throwable t) {
                snackbar = Snackbar.make(anchorView,"Error" , Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.CYAN);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.DKGRAY);
                TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();
            }
        });
        swipeLayout.setRefreshing(false);// stop the refreshing circle
    }


    @Override
    public void onRefresh() {
        get_msg(rootView, API_KEY); // call get message to retrieve messages on refresh
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
