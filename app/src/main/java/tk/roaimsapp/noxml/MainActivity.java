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

public class MainActivity extends Activity implements OnClickListener, MenuItem.OnMenuItemClickListener
{
    
    private MainLayout layout;
    private Button btBt,btTv,btEt;
    private int btId=0,tvId=900,etId=90000;
    private ScrollView mainSV;
    
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
        MenuItem mi = menu.add("Info");
        mi.setIcon(android.R.drawable.ic_menu_info_details).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        mi.setOnMenuItemClickListener(this);   
        
        if(dialogInfo==null){
            dialogInfo = new Dialog(this);
            dialogInfo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogInfo.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogInfo.setCancelable(true);
            if(tvInfo==null){
                tvInfo = new TextView(this);
                tvInfo.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                tvInfo.setSelected(true);
                tvInfo.setPadding(10,10,10,10);
                tvInfo.setTextColor(Color.WHITE);
                tvInfo.setTextSize(15);
                tvInfo.setText(Html.fromHtml(info));
                tvInfo.setOnClickListener(new DynamicClickListener());
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
                bt.setId(btId);
                bt.setText("Button: "+btId);
                layout.addView(bt);
                bt.setOnClickListener(new DynamicClickListener());
                btId++;
            } break;
            case MainLayout.ID+1:{
                TextView tv = new TextView(this);
                tv.setId(tvId);
                tv.setText("TextView: "+(tvId-900));
                layout.addView(tv);
                tvId++;
            } break;
            case MainLayout.ID+2:{
                EditText et = new EditText(this);
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
            if((TextView)p1==tvInfo){
                dialogInfo.dismiss();
                return;
            }
            int id = p1.getId();
            int idTv = 900+id;
            int idEt = 90000+id;
            TextView tv = (TextView) layout.findViewById(idTv);
            EditText et = (EditText) layout.findViewById(idEt);
            if(et!=null){
                String text = et.getText().toString();
                if(tv!=null){
                    tv.setText(text);
                } else mkt("TextView not found");
            } else mkt("Edittext not found");
        }
        
    }

    private void mkt ( String id ) {
        Toast.makeText(this,id,0).show();
    }
 
}
