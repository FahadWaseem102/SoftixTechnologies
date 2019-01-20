package com.example.fahad.softixtechnologies.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.fahad.softixtechnologies.Helpers.DatabaseHelper;
import com.example.fahad.softixtechnologies.R;
import com.example.fahad.softixtechnologies.models.ModelAccounts;

import java.util.ArrayList;
import java.util.List;

public class AccountsAdapter extends BaseAdapter {

        private DatabaseHelper dbhelper ;
        private Context context;
        private List<ModelAccounts> mModelAccounts;

    public AccountsAdapter(Context context, List < ModelAccounts > mModelAccounts) {
        this.context = context;
        this.mModelAccounts = mModelAccounts;
    }

        @Override
        public int getCount () {
        return mModelAccounts.size();
    }

        @Override
        public Object getItem ( int position){
        return mModelAccounts.get(position);
    }

        @Override
        public long getItemId ( int position){
        return position;
    }

        @Override
        public View getView ( final int position, View convertView, ViewGroup parent){

        View view = View.inflate(context, R.layout.account_list_item, null);
        ImageView image = view.findViewById(R.id.imageView);
        TextView textName = view.findViewById(R.id.textView_name);
        TextView textBlnc = view.findViewById(R.id.textView_blnc);

        textName.setText(mModelAccounts.get(position).getAccName());
        textBlnc.setText("Op. Blnc = " + mModelAccounts.get(position).getOpnBlnc() + " Rs.");

        try {
            String firstNameChar = textName.getText().toString();
            String firstletter = String.valueOf(firstNameChar.charAt(0));
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color = generator.getColor(getItem(position));
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(firstletter, color);
            image.setImageDrawable(drawable);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return view;
    }
}
