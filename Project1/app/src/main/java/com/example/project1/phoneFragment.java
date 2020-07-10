package com.example.project1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class phoneFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView listView;
    private phoneFragment.ContactAdapter adapter;

    public phoneFragment() {
        // Required empty public constructor
    }

    public static phoneFragment newInstance(String param1, String param2) {
        phoneFragment fragment = new phoneFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //private List<PhoneBook> phoneBooks;
    private List<SingleItem> loaded_items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // return inflater.inflate(R.layout.fragment_phone, container, false);
        // fragment_phone의 ROOT VIEW type 은 FrameLayout 이던데, ViewGroup 이 관련있는 것 같다.
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_phone, container, false);

        loaded_items = SingleItem.getContacts(getActivity());

        listView = (ListView) view.findViewById(R.id.contact_view);

        adapter = new ContactAdapter();

        //adapter.addItem(new PhoneBook("24","auaicn","010-8506-4538"));
        //adapter.addItem(new SingleItem("auaicn","01085064538",24,R.drawable.animal1));

        /*
        for (int i=0;i<phoneBooks.size();i++){
            adapter.addItem(new PhoneBook(phoneBooks.get(i).getId(),phoneBooks.get(i).getName(),phoneBooks.get(i).getTel()));
        }
        */
        System.out.println("--------------------------------before----------------------------------------------------------------------------");

        for (int i=0;i<loaded_items.size();i++){
            adapter.addItem(new SingleItem(loaded_items.get(i).getName(),loaded_items.get(i).getMobile(),loaded_items.get(i).getAge(),loaded_items.get(i).getResId()));
            //adapter.addItem(new SingleItem(loaded_items.get(i).getName(),loaded_items.get(i).getMobile(),loaded_items.get(i).getAge()));
        }
        System.out.println("-------------------------------after-----------------------------------------------------------------------------");
        System.out.println("auaicn" + loaded_items.size());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SingleItem item = (SingleItem) adapter.getItem(position);
                Toast.makeText(getActivity(),"선택 : " + item.getName(), Toast.LENGTH_LONG).show();
            }

        });

        return view;
    }

   public class ContactAdapter extends BaseAdapter{
        ArrayList<SingleItem> items = new ArrayList<SingleItem>();
       //ArrayList<PhoneBook> items = new ArrayList<PhoneBook>();

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
       }

        /*
        // not overriding here
        public void addItem(PhoneBook item){
            items.add(item);
        }
        */
        public void addItem(SingleItem item){
            items.add(item);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            SingleItemView view = new SingleItemView(getActivity());
            //PhoneBook item = items.get(position);
            SingleItem item = items.get(position);
            view.setText1(item.getName());
            view.setText2(item.getMobile());
            view.setText3(item.getAge());
            // view.setText2(item.getTel());
            // view.setText3(Integer.parseInt(item.getId()));
            // view.setImage(ge);
            view.setImage(item.getResId());
            return view;
        }
    }

}