package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private ListView listView;
    private phoneFragment.ContactAdapter adapter;

    public static phoneFragment newInstance(String param1, String param2) {
        phoneFragment fragment = new phoneFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private List<SingleItem> loaded_items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // View Group
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_phone, container, false);
        listView = (ListView) view.findViewById(R.id.contact_list_view);

        // Contact APP 에 접근해서, 연락처 정보를 쿼리를 통해 LIST 형태로 가져온다.
        loaded_items = SingleItem.getContacts(getActivity());
        System.out.println(loaded_items.size() + " elements loaded by getContacts()"); // debugging.

        // Custom adapter Object 생성
        adapter = new ContactAdapter();
        Bundle bundle;


        // Custom adapter Object 에 load한 연락처 아이템 하나하나를 추가해준다.
        // addItem() on class "SingleItem" which is sub-class of current class.
        for (int i=0;i<loaded_items.size();i++) {
            System.out.println(loaded_items.get(i));
            adapter.addItem(new SingleItem(
                    loaded_items.get(i).getPHOTO_URI(),
                    loaded_items.get(i).getDISPLAY_NAME(),
                    loaded_items.get(i).getCOMPANY(),
                    loaded_items.get(i).getNUMBER(),
                    loaded_items.get(i).getADDRESS(),
                    loaded_items.get(i).getURL(),
                    loaded_items.get(i).getNOTE(),
                    loaded_items.get(i).getIS_PRIMARY()));
        }
        // Object 의 추가가 완료되면, adapter 를 설정해준다.
        listView.setAdapter(adapter);

        // listView 에는, 각 item 이 눌리는 것을 event 로 처리해줄 수 있는데,
        // Toast 메시지를 출력할 수 있게 설정해준 상태이다.
        // list 자체가 눌리는 이벤트를 처리해주는 함수는 setOnClickListener 이다.
        // 우선순위는 list 자체가 눌리는 이벤트가 우선인 것 같다. 그래서 정의해주지 않았다.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                SingleItem item = (SingleItem) adapter.getItem(position);
                // Toast.makeText(getActivity(),"선택 : " + item.getName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), profile_description.class);
                Log.d("auaicn","Starting activity succeed");
                intent.putExtras(bundle);
                if (intent.putExtra("profile_in_detail",bundle) == null)
                    Log.d("auaicn","intent putting extra failed");
                else
                    Log.d("auaicn","intent putting extra succeed");
                startActivity(intent);
                Log.d("auaicn","Starting activity succeed");
            }
        });

        return view;
    }

   public class ContactAdapter extends BaseAdapter{
        // mandatory override
           // 1. getCount()
           // 2. getItem()
           // 3. getItemId()
           // 4. getView()

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
       }

        @Override
        public long getItemId(int position) {
            return position;
        }

        ArrayList<SingleItem> items = new ArrayList<SingleItem>();

       public void addItem(SingleItem item){
           items.add(item);
       }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            SingleItemView view = new SingleItemView(getActivity());

            SingleItem item = items.get(position);
            view.setContact_name(item.getDISPLAY_NAME());

            return view;
        }
    }

}