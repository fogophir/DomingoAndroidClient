package Domingo.OA.domingoandroidclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PostListAdapter extends BaseAdapter {

    Context context;
    String[] data;
    private static LayoutInflater inflater = null;

    public PostListAdapter(Context context, String[] data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        // getting the row layout for each item
        if (vi == null)
            vi = inflater.inflate(R.layout.post_list_item, null);

        // insert info into row
        TextView textFirstLine = (TextView) vi.findViewById(R.id.firstLine);
        TextView textSecondLine = (TextView) vi.findViewById(R.id.secondLine);
        ImageView image = (ImageView) vi.findViewById(R.id.icon);



        return vi;
    }
}
