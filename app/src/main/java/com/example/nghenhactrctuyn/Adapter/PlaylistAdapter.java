package com.example.nghenhactrctuyn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nghenhactrctuyn.Model.Playlist;
import com.example.nghenhactrctuyn.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }
    class viewholder{
        TextView txttenplaylist;
        ImageView imgbackgroudplaylist,imghinhplaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewholder viewholder=null;
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.dong_playlist,null);
            viewholder=new viewholder();
            viewholder.txttenplaylist=convertView.findViewById(R.id.textviewtenplaylist);
            viewholder.imgbackgroudplaylist=convertView.findViewById(R.id.imageviewbackgroudplaylist);
            viewholder.imghinhplaylist=convertView.findViewById(R.id.imageviewplaylist);
            convertView.setTag(viewholder);
        }
        else {
            viewholder= (PlaylistAdapter.viewholder) convertView.getTag();
        }
        Playlist playlist=getItem(position);
        Picasso.with(getContext()).load(playlist.getHinhPlayList()).into(viewholder.imgbackgroudplaylist);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewholder.imghinhplaylist);
        viewholder.txttenplaylist.setText(playlist.getTen());
        return convertView;
    }
}
