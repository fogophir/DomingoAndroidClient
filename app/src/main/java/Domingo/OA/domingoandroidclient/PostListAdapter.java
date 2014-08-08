package domingo.oa.domingoandroidclient;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private JSONArray data;
    private static LayoutInflater inflater = null;

    private static Drawable ic_loading = null;

    public PostListAdapter(Context context, JSONArray data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
            return data.length();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        // TODO Auto-generated method stub
        try {
            return data.getJSONObject(i);
        }
        catch (JSONException e){
            return null;
        }
    }

    @Override
    public Object getChild(int i, int i2) {
        // TODO Auto-generated method stub
        return getGroup(i);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int position, boolean isExpanded, View view, ViewGroup viewGroup) {
        View vi = view;

        if (vi == null)
            vi = inflater.inflate(R.layout.post_list_item, null);

        // finding view's elements to set
        TextView textFirstLine = (TextView) vi.findViewById(R.id.firstLine);
        TextView textSecondLine = (TextView) vi.findViewById(R.id.secondLine);
        SmartImageView image = (SmartImageView) vi.findViewById(R.id.icon);
        // setting image as loading until fetch
        image.setImageResource(R.drawable.ic_loading);

        // setting values
        JSONObject obj = (JSONObject)getGroup(position);

        try {
            image.setImageUrl(obj.getString("ThumbImage"));

            textFirstLine.setText(obj.getString("UserFullName"));
            textSecondLine.setText(obj.getString("CaptionText"));
        }
        catch (JSONException e){
            Log.w("Error", e.getMessage());
        }

        return vi;
    }

    @Override
    public View getChildView(int position, int i2, boolean b, View view, ViewGroup viewGroup) {
        View vi = view;

        // getting the row layout for each item if null (if not, already has the object)
        if (vi == null)
            vi = inflater.inflate(R.layout.post_list_item_expanded, null);

        // finding view's elements to set
        TextView lblListItem = (TextView) vi.findViewById(R.id.lblListItem);

        // setting values
        JSONObject obj = (JSONObject)getGroup(position);

        try {
            String dateStr = DateHelper.unixToDate(obj.getString("CreatedTime"));
            lblListItem.setText(dateStr);
        }
        catch (JSONException e){
            Log.w("Error", e.getMessage());
        }

        return vi;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }
}