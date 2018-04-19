package com.example.yomna.symptomate;

/**
 * Created by Yomna on 12/1/2017.
 */

import android.content.Context;
import android.icu.text.LocaleDisplayNames;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {
    private Context context;
    ListView chatList;
    Button male;
    Button female;
    Button nocorrect;
    Button yesgo;
    Button yes;
    Button no;
    Button donnu;
    Button yesmore;
    Button nogo;
    RecyclerView myRecyclerView;
    RecyclerView.LayoutManager myLayoutManager;
    RecyclerView.Adapter myAdapter;
    ArrayList<String> myDataset;
    Button start_again;
    List<ChatMessage> data;
    @Override
    public void add(ChatMessage object) {
        super.add(object);
    }
    public ChatArrayAdapter(Context context, List<ChatMessage> data,ListView chatList) {
        super(context, R.layout.sent_msg, data);
        this.data=data;
        this.context=context;
        this.chatList=chatList;
    }
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        int viewType = getItem(position).isMine();
        if (viewType==0 ) {

            if( getItem(position-1).isMine()!=1){
                getItem(position-1).setIsMine(1);
            }
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sent_msg, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.sent_msg);
            textView.setText(getItem(position).getMessage());
            TextView time = (TextView) convertView.findViewById(R.id.time);
            String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
            time.setText(mydate);
            textView.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
        }
        if (viewType==1 ) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recieved_msg, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.recieved_msg);
            textView.setText(getItem(position).getMessage());
            TextView time = (TextView) convertView.findViewById(R.id.time);
            String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
            time.setText(mydate);
            textView.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
        }
        if (viewType==2 ) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card4, parent, false);
            TextView textView2 = (TextView) convertView.findViewById(R.id.textView2);
            textView2.setText(getItem(position).getMessage());
            male= (Button) convertView.findViewById(R.id.male1);
            male.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    getItem(getCount()-1).setIsMine(1);
                    JSONObject jsn=new JSONObject();
                    try {
                        jsn.put("message","male");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MainActivity cont = (MainActivity) context;
                    new Chat(cont).execute(cont.Authorization,jsn.toString());
                    ChatMessage chatMessage = new ChatMessage(0, "male");
                    add(chatMessage);
                    add(new ChatMessage(1,"typing..."));
                    chatList.setSelection(getCount() - 1);
                }
            });
            female= (Button) convertView.findViewById(R.id.female);
            female.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    getItem(getCount()-1).setIsMine(1);
                    ChatMessage chatMessage = new ChatMessage(0, "female");
                    add(chatMessage);
                    JSONObject jsn=new JSONObject();
                    try {
                        jsn.put("message","female");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MainActivity cont = (MainActivity) context;
                    new Chat(cont).execute(cont.Authorization,jsn.toString());
                    add(new ChatMessage(1,"typing..."));
                    chatList.setSelection(getCount() - 1);
                }
            });
        }
        if (viewType==3 ) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card1, parent, false);
            TextView textView2 = (TextView) convertView.findViewById(R.id.textView);
            textView2.setText(getItem(position).getMessage());
            yesgo= (Button) convertView.findViewById(R.id.cardbtn1);
            yesgo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    getItem(getCount()-1).setIsMine(1);
                    JSONObject jsn=new JSONObject();
                    try {
                        jsn.put("message","yes,go on");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MainActivity cont = (MainActivity) context;
                    new Chat(cont).execute(cont.Authorization,jsn.toString());
                    ChatMessage chatMessage = new ChatMessage(0, "Yes,go on");
                    add(chatMessage);
                    add(new ChatMessage(1,"typing..."));

                    chatList.setSelection(getCount() - 1);
                }
            });
            nocorrect= (Button) convertView.findViewById(R.id.cardbtn2);
            nocorrect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    getItem(getCount()-1).setIsMine(1);
                    ChatMessage chatMessage = new ChatMessage(0, "No,add more");
                    add(chatMessage);
                    JSONObject jsn=new JSONObject();
                    try {
                        jsn.put("message","No,add more");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MainActivity cont = (MainActivity) context;
                    new Chat(cont).execute(cont.Authorization,jsn.toString());
                    add(new ChatMessage(1,"typing..."));
                    chatList.setSelection(getCount() - 1);
                }
            });
        }
        if(viewType==4){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card2, parent, false);
            TextView textView2 = (TextView) convertView.findViewById(R.id.textView);
            textView2.setText(getItem(position).getMessage());
            nogo= (Button) convertView.findViewById(R.id.cardbtn2);
            nogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    getItem(getCount()-1).setIsMine(1);
                    JSONObject jsn=new JSONObject();
                    try {
                        jsn.put("message","No,let's go on");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MainActivity cont = (MainActivity) context;
                    new Chat(cont).execute(cont.Authorization,jsn.toString());
                    ChatMessage chatMessage = new ChatMessage(0, "No,let's go on");
                    add(chatMessage);
                    add(new ChatMessage(1,"typing..."));
                    chatList.setSelection(getCount() - 1);
                }
            });
            yesmore= (Button) convertView.findViewById(R.id.cardbtn1);
            yesmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    getItem(getCount()-1).setIsMine(1);
                    ChatMessage chatMessage = new ChatMessage(0, "Yes,there's more");
                    add(chatMessage);
                    JSONObject jsn=new JSONObject();
                    try {
                        jsn.put("message","Yes,there's more");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MainActivity cont = (MainActivity) context;
                    new Chat(cont).execute(cont.Authorization,jsn.toString());
                    add(new ChatMessage(1,"typing..."));
                    chatList.setSelection(getCount() - 1);
                }
            });
        }
        if(viewType==5){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.yesandno, parent, false);
            TextView textView2 = (TextView) convertView.findViewById(R.id.textView2);
            textView2.setText(getItem(position).getMessage());
            yes= (Button) convertView.findViewById(R.id.cardbtn1);
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    getItem(getCount()-1).setIsMine(1);
                    JSONObject jsn=new JSONObject();
                    try {
                        jsn.put("message","yes");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MainActivity cont = (MainActivity) context;
                    new Chat(cont).execute(cont.Authorization,jsn.toString());
                    ChatMessage chatMessage = new ChatMessage(0, "Yes");
                    add(chatMessage);
                    add(new ChatMessage(1,"typing..."));
                    chatList.setSelection(getCount() - 1);
                }
            });
            no= (Button) convertView.findViewById(R.id.cardbtn2);
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    getItem(getCount()-1).setIsMine(1);
                    JSONObject jsn=new JSONObject();
                    try {
                        jsn.put("message","no");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MainActivity cont = (MainActivity) context;
                    new Chat(cont).execute(cont.Authorization,jsn.toString());
                    ChatMessage chatMessage = new ChatMessage(0, "No");
                    add(chatMessage);
                    add(new ChatMessage(1,"typing..."));
                    chatList.setSelection(getCount() - 1);
                }
            });


            donnu= (Button) convertView.findViewById(R.id.cardbtn3);
            donnu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    getItem(getCount()-1).setIsMine(1);
                    JSONObject jsn=new JSONObject();

                    try {
                        jsn.put("message","don't know");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MainActivity cont = (MainActivity) context;
                    new Chat(cont).execute(cont.Authorization,jsn.toString());
                    ChatMessage chatMessage = new ChatMessage(0, "I don't know");
                    add(chatMessage);
                    add(new ChatMessage(1,"typing..."));
                    chatList.setSelection(getCount() - 1);
                }
            });

        }
        if(viewType==6){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.results, parent, false);
            myDataset = new ArrayList<>();
            String[] str= getItem(position).getMessage().split("\\r?\\n");
            for (int i = 1; i < str.length ; i++) {
                myDataset.add(str[i]);
            }

            myRecyclerView = (RecyclerView) convertView.findViewById(R.id.recycler_view);
            myRecyclerView.setHasFixedSize(true);
            myLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            myRecyclerView.setLayoutManager(myLayoutManager);
            myAdapter = new MyAdapter(myDataset);
            myRecyclerView.setAdapter(myAdapter);
            start_again= (Button) convertView.findViewById(R.id.start_again);
            start_again.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {

                    MainActivity cont = (MainActivity) context;
                      data.removeAll(data);
                     notifyDataSetChanged();
                    new Welcome(context).execute();
                }
            });
        }
        return convertView;
    }
}