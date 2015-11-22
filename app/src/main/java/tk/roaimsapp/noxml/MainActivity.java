package tk.roaimsapp.noxml;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.graphics.Color;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Toast;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ActionMenuView.OnMenuItemClickListener;
import android.app.Dialog;
import android.view.Window;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.widget.PopupMenu;

public class MainActivity extends Activity implements OnClickListener, MenuItem.OnMenuItemClickListener
{
    private static final String TAG_BT="BT",TAG_TV="TV",TAG_ET="ET";
    private MainLayout layout;
    private Button btBt,btTv,btEt;
    private int btId=0,tvId=900,etId=90000;
    private ScrollView mainSV;
    private View cv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(layout==null || mainSV==null){
            layout = new MainLayout(this);
            btBt = layout.btBt;
            btTv = layout.btTv;
            btEt = layout.btEt;
            mainSV = new ScrollView(this);
            mainSV.setLayoutParams(layout.lp);
            mainSV.addView(layout);
        }
        setContentView(mainSV);
        btBt.setOnClickListener(this);
        btTv.setOnClickListener(this);
        btEt.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu ( Menu menu ) {
        // TODO: Implement this method
        menu.add ( "Clear" ).setIcon ( android.R.drawable.ic_menu_delete ).setOnMenuItemClickListener ( this ).setShowAsAction ( MenuItem.SHOW_AS_ACTION_ALWAYS );
        MenuItem mi = menu.add ( "Info" );
        mi.setIcon(android.R.drawable.ic_menu_info_details).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        mi.setOnMenuItemClickListener(this);   
        
        if(dialogInfo==null){
            dialogInfo = new Dialog(this);
            dialogInfo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogInfo.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogInfo.setCancelable(true);
            if(tvInfo==null){
                tvInfo = new TextView(this);
                tvInfo.setMovementMethod(new ScrollingMovementMethod());
                tvInfo.setPadding(10,10,10,10);
                tvInfo.setTextColor(Color.WHITE);
                tvInfo.setTextSize(15);
                tvInfo.setText(Html.fromHtml(info));
            }
            dialogInfo.setContentView(tvInfo);
        }
        return super.onCreateOptionsMenu ( menu );
    }
    
    private static final String info = "<h1><font color=\"#0099ff\">Instructions:</font><h1><p>There are 3 buttons on the main screen:</p><p><b>Add Button:</b> Tapping it creates infinite buttons with specific ids dynamically. Tapping a newly created button will get text from edit text of same id and show it in the text view of same id.</p><p><b>Add TextView:</b> It creates infinite TextViews with specific ids dynamically.</p><p><b>Add EditText:</b> It creates infinite EditTexts with specific ids dynamically.</p><h1><font color=\"#00cc00\">Developer:</font></h1><p>Roaim Ahmed <b>Hridoy</b><br>Noakhali Science and Technology University.</p>";
    private TextView tvInfo;
    private Dialog dialogInfo;

    @Override
    public boolean onMenuItemClick ( MenuItem p1 ) {
        // TODO: Implement this method
        String title = p1.getTitle().toString();
        
        if(title.equals("Clear")){
            if(cv==null)
            cv=findViewById(p1.getItemId());
            PopupMenu pm = new PopupMenu(this,cv);
            pm.getMenu().add("Clear last view").setOnMenuItemClickListener(this);
            pm.getMenu().add("Clear all views").setOnMenuItemClickListener(this);
            pm.show();
        } else if(title.equals("Clear last view")){
            View v = layout.lastView();
            if(v==null) return true;
            String tag = (String) v.getTag();
            if(tag==TAG_BT){
                btId--;
            } else if(tag==TAG_TV){
                tvId--;
            } else if(tag==TAG_ET){
                etId--;
            }
            layout.clearLast();
        } else if(title.equals("Clear all views")){
            layout.clearAll();
            btId=0;
            tvId=900;
            etId=90000;
        } else if(title.equals("Info"))
                dialogInfo.show();
        return true;
    }

    @Override
    public void onClick ( View p1 ) {
        // TODO: Implement this method
        int id=p1.getId();
        switch (id){
            case MainLayout.ID:{
                Button bt = new Button(this);
                bt.setTag(TAG_BT);
                bt.setId(btId);
                bt.setText("Button: "+btId);
                layout.addView(bt);
                bt.setOnClickListener(new DynamicClickListener());
                btId++;
            } break;
            case MainLayout.ID+1:{
                TextView tv = new TextView(this);
                tv.setTag(TAG_TV);
                tv.setId(tvId);
                tv.setText("TextView: "+(tvId-900));
                layout.addView(tv);
                tvId++;
            } break;
            case MainLayout.ID+2:{
                EditText et = new EditText(this);
                et.setTag(TAG_ET);
                et.setId(etId);
                et.setHint("EditText: "+(etId-90000));
                layout.addView(et);
                etId++;
            }
        }
    }
    
    private class DynamicClickListener implements OnClickListener {

        @Override
        public void onClick ( View p1 ) {
            int id = p1.getId();
            int idTv = 900+id;
            int idEt = 90000+id;
            TextView tv = (TextView) layout.findViewById(idTv);
            EditText et = (EditText) layout.findViewById(idEt);
            if(et!=null){
                String text = et.getText().toString();
                if(tv!=null){
                    tv.setText(text);
                } else mkt("TextView: "+id+" not found");
            } else mkt("Edittext: "+id+" not found");
        }
        
    }

    private void mkt ( String id ) {
        Toast.makeText(this,id,0).show();
    }
 
}
