package recyclerview.com;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyViewHolder> {
    public static final String EXTRA_TEXT1="key1";
    public static final String EXTRA_TEXT2="key2";
    public static final String EXTRA_TEXT3="key3";
    public static final String EXTRA_TEXT4="key4";
    public static final String EXTRA_TEXT5="key5";
    public static final Contact cont = null;
     List<Contact> ls;
    ArrayList<String> imgs;
    Context c;
    Instant in;
    public MyRvAdapter(List<Contact> ls, Context c) {
        this.c=c;
        this.ls=ls;
    }
    public MyRvAdapter(List<Contact> ls, Context c,Instant in) {
        this.c=c;
        this.ls=ls;
        this.in=in;

    }

    @NonNull
    @Override
    public MyRvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(c).inflate(R.layout.row,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRvAdapter.MyViewHolder holder, final int position) {
        holder.name.setText(ls.get(position).getName());
        holder.number.setText(ls.get(position).getNumber());
        holder.email.setText(ls.get(position).getEmail());
        holder.address.setText(ls.get(position).getAddress());
        if((ls.get(position).getImageUri()!=null))
        {
            {
                Glide.with(c)
                        .load(ls.get(position).getImageUri())
                        .into(holder.xyz);
            }
        }


        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c,ls.get(position).getName(),Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(c,view_contact.class);
                intent.putExtra(EXTRA_TEXT1,ls.get(position).getName());
                intent.putExtra(EXTRA_TEXT2,ls.get(position).getNumber());
                intent.putExtra(EXTRA_TEXT3,ls.get(position).getEmail());
                intent.putExtra(EXTRA_TEXT4,ls.get(position).getAddress());
                intent.putExtra(EXTRA_TEXT5,ls.get(position).getImageUri());
                c.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,email,number,address;
        LinearLayout ll;
        CircleImageView xyz;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            number=itemView.findViewById(R.id.number);
            address=itemView.findViewById(R.id.address);
            ll=itemView.findViewById(R.id.ll);
            xyz= itemView.findViewById(R.id.row_profile);
        }
    }
    public void filterList (ArrayList<Contact>filteredList){
        ls=filteredList;
        notifyDataSetChanged();
    }
}
