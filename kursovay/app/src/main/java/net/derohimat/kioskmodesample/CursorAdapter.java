package net.derohimat.kioskmodesample;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CursorAdapter extends RecyclerView.Adapter<CursorAdapter.CursorViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public CursorAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class CursorViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public TextView countText;

        public CursorViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.name);

        }
    }

    @Override
    public CursorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.user_item, parent, false);
        return new CursorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CursorViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        String name = mCursor.getString(mCursor.getColumnIndex("name"));

        holder.nameText.setText(name);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}