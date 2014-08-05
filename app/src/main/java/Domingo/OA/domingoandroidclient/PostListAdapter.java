package domingo.oa.domingoandroidclient;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostListAdapter extends BaseAdapter {

    private Context context;
    private JSONArray data;
    private static LayoutInflater inflater = null;

    public PostListAdapter(Context context, JSONArray data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.length();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        try {
            return data.getJSONObject(position);
        }
        catch (JSONException e){
            return null;
        }
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

        // finding view's elements to set
        TextView textFirstLine = (TextView) vi.findViewById(R.id.firstLine);
        TextView textSecondLine = (TextView) vi.findViewById(R.id.secondLine);
        ImageView image = (ImageView) vi.findViewById(R.id.icon);

        // setting values
        JSONObject obj = (JSONObject)getItem(position);

        try {
            ImagesUtil.SetImageFromUrlToImageView(obj.getString("ThumbImage"),image);
            textFirstLine.setText(obj.getString("UserFullName"));
            textSecondLine.setText(obj.getString("CaptionText"));
        }
        catch (JSONException e){
            Log.w("Error", e.getMessage());
        }

        return vi;
    }
}