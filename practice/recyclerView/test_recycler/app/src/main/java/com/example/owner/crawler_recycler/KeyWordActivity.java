package com.example.owner.crawler_recycler;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.owner.crawler_recycler.Adapter.KeyWordAdapter;
import com.example.owner.crawler_recycler.Model.KeyWordInfo;
import com.example.owner.crawler_recycler.databinding.ActivityKeyWordBinding;
import com.example.owner.crawler_recycler.dialog.KwAddDialog;

import java.util.ArrayList;
import java.util.List;

public class KeyWordActivity extends AppCompatActivity {


    private static final int LAYOUT = R.layout.activity_key_word;
    private ActivityKeyWordBinding keyWordBinding;
    //private RecyclerView.Adapter adapter;
    private KeyWordAdapter adapter;
    private ArrayList<KeyWordInfo> data = new ArrayList<>();

    private AlertDialog.Builder alert;
    private KwAddDialog kwAddDialog;
    private ArrayList<KeyWordInfo> server = new ArrayList<>();

    View.OnClickListener leftClickListener;
    View.OnClickListener rightClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_word);
        keyWordBinding = DataBindingUtil.setContentView(this, LAYOUT);
        alert  = new AlertDialog.Builder(this);

        leftClickListener =new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kwAddDialog.cancel();
            }
        };
        rightClickListener =new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kwAddDialog.getEdit1Str().isEmpty()){
                    Toast.makeText(getApplicationContext(), "keyword is empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    server.add(new KeyWordInfo("pushToken", kwAddDialog.getEdit1Str(), keyWordBinding.crawlerTitleTxt.getText().toString(), "url", false));
                    setData();
                    kwAddDialog.dismiss();
                }
            }
        };


        kwAddDialog = new KwAddDialog(this, keyWordBinding.crawlerTitleTxt.getText().toString(), "keyword", leftClickListener, rightClickListener);

        //intent로 받은 crawler를 crawler title에 넣기



        setRecyclerView();
    }

    private void setRecyclerView(){
        keyWordBinding.keywordRecyclerView.setHasFixedSize(false);
        adapter = new KeyWordAdapter(data);
        keyWordBinding.keywordRecyclerView.setAdapter(adapter);
        adapter.setItemClick(new KeyWordAdapter.ItemClick(){
            @Override
            public void onClick(KeyWordInfo kw, int position) {
                if(kw.getFunct() == true){
                    //추가
                    Toast.makeText(getApplicationContext(),position+" ",Toast.LENGTH_SHORT).show();

//                    View mView = getLayoutInflater().inflate(R.layout.dialog_addkw, null);
//                    final EditText mkeyword = (EditText) mView.findViewById(R.id.keyword_edt);
                    kwAddDialog.show();

//                    //dialog를 통한 keyword입력 받기
//                        //확인
//                    server.add(new KeyWordInfo("pushToken", "kw"/*keyword*/ , keyWordBinding.crawlerTitleTxt.getText().toString(), "url", false));
//                    setData();
//
//                        //취소


                } else{

                }

            }

            @Override
            public void onLongClick(KeyWordInfo kw, int position){
                final int pos = position;
                if(kw.getFunct() == true){

                } else{
                    //삭제
                    alert.setTitle("alert");
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //확인
                            server.remove(pos);
                            setData();
                        }
                    });
                    alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //취소
                        }
                    });
                    alert.show();
                }
            }
        });

        keyWordBinding.keywordRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), new LinearLayoutManager(this).getOrientation());
        keyWordBinding.keywordRecyclerView.addItemDecoration(dividerItemDecoration);

        setData();
    }

    private void setData(){
        data.clear();
// RecyclerView 에 들어갈 데이터를 추가합니다.
        for(KeyWordInfo keyword : server){
            data.add(new KeyWordInfo(keyword.getPushToken(), keyword.getKeyword(), keyword.getCrawler(), keyword.getUrl(), keyword.getFunct()));
        }
        data.add(new KeyWordInfo(null, "add", null, null, true));

// 데이터 추가가 완료되었으면 notifyDataSetChanged() 메서드를 호출해 데이터 변경 체크를 실행합니다.
        adapter.notifyDataSetChanged();
    }
}
