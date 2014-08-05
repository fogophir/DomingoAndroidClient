package domingo.oa.domingoandroidclient;

import android.app.Activity;
import android.app.ListFragment;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;

public class PostListActivity extends Activity implements ConnectionHelper.IConnectionCallback {

    PostListFragment _fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        _fragment = new PostListFragment();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, _fragment)
                    .commit();
        }

        handleIntent(getIntent());
    }

    public void searchTag(String searchTerm){
        new ConnectionHelper().ExecuteHttpRequestAsync("searchTag","q=" + searchTerm, this);
    }

    @Override
    public void Callback(Exception error, String JSONresult) {
        try {
            if (error == null) {
                JSONArray jObject = new JSONArray(JSONresult);
                PostListAdapter adapter = new PostListAdapter(this, jObject);
                _fragment.setListAdapter(adapter);
            }
            else{
                //ERROR
            }
        }
        catch (JSONException e){
            //error with json
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        // get the intent, verify the action and get the query
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchTag(query);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.post_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_search){
            onSearchRequested();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * The fragment which contains the list
     */
    public static class PostListFragment extends ListFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_post_list, container, false);
            return rootView;
        }
    }
}
